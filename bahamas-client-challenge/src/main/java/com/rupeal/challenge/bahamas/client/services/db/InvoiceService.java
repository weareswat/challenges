package com.rupeal.challenge.bahamas.client.services.db;

import com.rupeal.challenge.bahamas.client.model.ClientEntity;
import com.rupeal.challenge.bahamas.client.model.InvoiceEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collection;

@ApplicationScoped
public class InvoiceService extends DBService<Long, InvoiceEntity> {

    public InvoiceService() {
        super(null);
    }

    @Inject
    public InvoiceService(final EntityManager em) {
        super(em);
    }

    @Transactional
    public Collection<ClientEntity> retrieveInvoiceClients(long invoiceId){
        final InvoiceEntity invoiceEntity = find(invoiceId);
        return invoiceEntity == null ? null : invoiceEntity.getClients();
    }

    @Override
    Class<InvoiceEntity> getEntityClass() {
        return InvoiceEntity.class;
    }

}
