package com.cocus.challenge.bahamas.service.impl;

import com.cocus.challenge.bahamas.model.Client;
import com.cocus.challenge.bahamas.service.BahamasGovernmentService;
import org.springframework.stereotype.Service;

@Service
public class DefaultBahamasGovernmentService implements BahamasGovernmentService {

    @Override
    public Client save(Client client) {
        return client;
    }
}
