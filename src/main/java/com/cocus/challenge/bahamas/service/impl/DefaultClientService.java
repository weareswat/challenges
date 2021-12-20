package com.cocus.challenge.bahamas.service.impl;

import com.cocus.challenge.bahamas.exceptions.ClientNotFoundException;
import com.cocus.challenge.bahamas.model.Client;
import com.cocus.challenge.bahamas.repository.ClientRepository;
import com.cocus.challenge.bahamas.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultClientService implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public DefaultClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public Client retrieveClient(Long invoiceId){

        return clientRepository
                .find(invoiceId)
                .orElseThrow(ClientNotFoundException::new);

    }

    @Override
    public Client storeBahamasClient(Client client) {
        return null;
    }
}
