package com.cocus.challenge.bahamas.service.impl;

import com.cocus.challenge.bahamas.exceptions.ClientAlreadyExistsException;
import com.cocus.challenge.bahamas.exceptions.ClientNotFoundException;
import com.cocus.challenge.bahamas.entities.Client;
import com.cocus.challenge.bahamas.repository.ClientRepository;
import com.cocus.challenge.bahamas.service.BahamasGovernmentService;
import com.cocus.challenge.bahamas.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public Client retrieveClient(Long invoiceId){
        return clientRepository
            .findByCode(invoiceId)
            .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    @Transactional
    public Client storeBahamasClient(Client client) {
        clientRepository
            .findByCode(client.getCode())
            .ifPresent((savedClient) -> {
                throw new ClientAlreadyExistsException();
            });

        Client savedClient = clientRepository.save(client);
        bahamasGovernmentService.save(client);

        return savedClient;
    }
}
