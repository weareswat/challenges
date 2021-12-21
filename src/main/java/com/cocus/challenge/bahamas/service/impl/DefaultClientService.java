package com.cocus.challenge.bahamas.service.impl;

import com.cocus.challenge.bahamas.exceptions.ClientNotFoundException;
import com.cocus.challenge.bahamas.model.Client;
import com.cocus.challenge.bahamas.repository.ClientRepository;
import com.cocus.challenge.bahamas.service.BahamasGovernmentService;
import com.cocus.challenge.bahamas.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultClientService implements ClientService {

    private final ClientRepository clientRepository;
    private final BahamasGovernmentService bahamasGovernmentService;

    @Autowired
    public DefaultClientService(
            ClientRepository clientRepository,
            BahamasGovernmentService bahamasGovernmentService){
        this.clientRepository = clientRepository;
        this.bahamasGovernmentService = bahamasGovernmentService;
    }

    @Override
    public Client retrieveClient(String invoiceId){
        return clientRepository
            .findById(invoiceId)
            .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public Client storeBahamasClient(Client client) {
        return null;
    }
}
