package com.cocus.challenge.bahamas.model;

import com.cocus.challenge.bahamas.enums.AcceptedLanguages;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    private String code;

    private String name;

    private String email;

    private AcceptedLanguages language;

    private String address;

    private String city;

    @JsonProperty("postal_code")
    private String postalCode;

    private String country;

    @JsonProperty("fiscal_id")
    private String fiscalId;

    private String website;

    private String phone;

    private String fax;

//    @JsonProperty("preferred_contact")
//    private PreferredContact preferredContact;


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
