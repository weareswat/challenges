package com.rupeal.challenge.bahamas.client.services.db;

import com.rupeal.challenge.bahamas.client.model.ClientEntity;
import com.rupeal.challenge.bahamas.client.model.InvoiceEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class ClientService extends DBService<Long, ClientEntity> {

    public ClientService() {
        super(null);
    }

    @Inject
    public ClientService(final EntityManager em) {
        super(em);
    }

    @Transactional
    public ClientEntity createInvoiceClient(Long invoiceId, Long fiscalId, String clientName, String email) {
        ClientEntity clientEntity = find(fiscalId);
        if(clientEntity == null) {
            clientEntity = new ClientEntity(fiscalId, clientName, email);
            getEm().persist(clientEntity);
        }

        //for challenge purposes I persist the invoice as well, in case it does not exist yet.
        InvoiceEntity invoiceEntity = getEm().find(InvoiceEntity.class, invoiceId);
        if(invoiceEntity == null){
            invoiceEntity = new InvoiceEntity(invoiceId);
            getEm().persist(invoiceEntity);
        }

        invoiceEntity.addClient(clientEntity);
        return clientEntity;
    }

    @Override
    Class<ClientEntity> getEntityClass() {
        return ClientEntity.class;
    }

}
