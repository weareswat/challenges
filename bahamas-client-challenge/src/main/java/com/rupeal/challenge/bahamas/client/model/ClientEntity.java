package com.rupeal.challenge.bahamas.client.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    private Long fiscalId;

    @Basic
    private String clientName;

    @Basic
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "invoice_clients",
            joinColumns = @JoinColumn(name = "fk_fiscalId"),
            inverseJoinColumns = @JoinColumn(name = "fk_invoiceId"))
    @JsonManagedReference
    private Set<InvoiceEntity> invoices = new HashSet<>();

    public ClientEntity() {
    }

    public ClientEntity(final Long fiscalId, final String clientName, final String email) {
        this.fiscalId = fiscalId;
        this.clientName = clientName;
        this.email = email;
    }

    public Long getFiscalId() {
        return fiscalId;
    }

    public void setFiscalId(Long fiscalId) {
        this.fiscalId = fiscalId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<InvoiceEntity> getInvoices() {
        return invoices;
    }

    public boolean addInvoice(InvoiceEntity invoiceEntity) {
        return invoices.add(invoiceEntity) && invoiceEntity.addClient(this);
    }

    public boolean removeInvoice(InvoiceEntity invoiceEntity){
        return invoices.remove(invoiceEntity) && invoiceEntity.removeClient(this);
    }
}
