package com.luishipero.interview.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

/**
 * @author Luis Torres
 * @since 09-2020
 */
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uuid;

    @Column(name = "userId")
    private String _id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "user", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

    @Column(name = "month")
    private String month;

    public User() {
        month = LocalDateTime.now().toLocalDate().getMonth().toString();
    }

    public User(final String _id, final String name, final Address address, final String month) {
        super();
        this._id = _id;
        this.name = name;
        this.address = address;
        this.month = month;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getMonth() {
        return month;
    }

    public Address getAddress() {
        return address;
    }
}
