package com.rupeal.challenge.bahamas.client.services;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "invoice-registry")
public class InvoiceRegistryProperties {
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
