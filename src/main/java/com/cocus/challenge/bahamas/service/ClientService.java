package com.cocus.challenge.bahamas.service;

import com.cocus.challenge.bahamas.entities.Client;

public interface ClientService {

    Client retrieveClient(Long invoiceId);

    Client storeBahamasClient(Client client);

}
