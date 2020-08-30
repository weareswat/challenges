package com.rupeal.challenge.bahamas.client.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "invoice")
public class InvoiceEntity {

    @Id
    private Long invoiceId;

    @ManyToMany(mappedBy = "invoices")
    @JsonBackReference
    private Set<ClientEntity> clients = new HashSet<>();

    public InvoiceEntity() {
    }

    public InvoiceEntity(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Set<ClientEntity> getClients() {
        return clients;
    }

    public boolean addClient(ClientEntity client){
        return this.clients.add(client) && client.addInvoice(this);
    }

    public boolean removeClient(ClientEntity client){
        return this.clients.remove(client) && client.removeInvoice(this);
    }
}
