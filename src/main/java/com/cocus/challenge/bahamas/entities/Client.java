package com.cocus.challenge.bahamas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "CODE", unique = true, nullable = false)
    @JsonProperty("code")
    private Long code;

    @Column(name = "NAME", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name = "EMAIL")
    @JsonProperty("email")
    private String email;

    @Column(name = "LANGUAGE")
    @JsonProperty("language")
    private String language;

    @Column(name = "ADDRESS")
    @JsonProperty("address")
    private String address;

    @Column(name = "CITY")
    @JsonProperty("city")
    private String city;

    @Column(name = "POSTAL_CODE")
    @JsonProperty("postal_code")
    private String postalCode;

    @Column(name = "COUNTRY")
    @JsonProperty("country")
    private String country;

    @Column(name = "FISCAL_ID")
    @JsonProperty("fiscal_id")
    private String fiscalId;

    @Column(name = "WEBSITE")
    @JsonProperty("website")
    private String website;

    @Column(name = "PHONE")
    @JsonProperty("phone")
    private String phone;

    @Column(name = "FAX")
    @JsonProperty("fax")
    private String fax;

    @JsonProperty("preferred_contact")
    @OneToOne(cascade = CascadeType.ALL)
    private PreferredContact preferredContact;

    @Column(name = "OBSERVATIONS")
    @JsonProperty("observations")
    private String observations;

    @Column(name = "SEND_OPTIONS")
    @JsonProperty("send_options")
    private String sendOptions;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFiscalId() {
        return fiscalId;
    }

    public void setFiscalId(String fiscalId) {
        this.fiscalId = fiscalId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public PreferredContact getPreferredContact() {
        return preferredContact;
    }

    public void setPreferredContact(PreferredContact preferredContact) {
        this.preferredContact = preferredContact;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getSendOptions() {
        return sendOptions;
    }

    public void setSendOptions(String sendOptions) {
        this.sendOptions = sendOptions;
    }
}
