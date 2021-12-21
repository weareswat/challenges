package com.cocus.challenge.bahamas.service.impl;

import com.cocus.challenge.bahamas.entities.Client;
import com.cocus.challenge.bahamas.service.BahamasGovernmentService;
import org.springframework.stereotype.Service;

@Service
public class DefaultBahamasGovernmentService implements BahamasGovernmentService {

    @Override
    public Client save(Client client) {

        String bahamasGovUrl = "https://bahamas.gov/register";

        String url = String.format(bahamasGovUrl + "?invoice=%s&fiscal_id=%s&name=%s&email=%s",
                client.getCode(),
                client.getFiscalId(),
                client.getName(),
                client.getEmail());

        System.out.println("Send info to Bahamas Government -> " + url);

        return client;
    }
}
