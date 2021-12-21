package com.cocus.challenge.bahamas.service;

import com.cocus.challenge.bahamas.model.Client;

public interface ClientService {

    Client retrieveClient(String invoiceId);

    Client storeBahamasClient(Client client);

}
