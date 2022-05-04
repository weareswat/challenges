package com.cocus.bahamasapi.entities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CLIENT_TB")
public class Client {

    @Id
    @Column(name = "INVOICE_ID", unique = true, nullable = false, precision = 19, scale = 0)
    private Long invoiceId;

    @Column(name = "FISCAL_ID", nullable = false, precision = 9, scale = 0)
    private Integer fiscalId;

    @Column(name = "NAME", nullable = false, length = 70)
    private String name;

    @Column(name = "EMAIL", nullable = false, length = 320)
    private String email;

    @Override
    public String toString() {
        return "Client{" +
                "invoiceId=" + invoiceId +
                ", fiscalId=" + fiscalId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Client(Integer fiscalId, String name, String email) {
        this.fiscalId = fiscalId;
        this.name = name;
        this.email = email;
    }
}
