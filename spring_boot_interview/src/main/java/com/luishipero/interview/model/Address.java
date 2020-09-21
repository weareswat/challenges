package com.luishipero.interview.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;


/**
 * @author Luis Torres
 * @since 09-2020
 */
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uuid;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "uuid")
    private User user;

    @Column(name = "street")
    private String street;
    public Address() {}

    public Address(final User user, final String street) {
       super();
       this.user = user;
       this.street = street;
    }

    public String getStreet() {
       return street;
    }
}
