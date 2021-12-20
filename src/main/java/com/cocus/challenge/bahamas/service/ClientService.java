package com.cocus.challenge.bahamas.service;

import com.cocus.challenge.bahamas.exceptions.ClientNotFoundException;
import com.cocus.challenge.bahamas.model.Client;

public interface ClientService {

    Client retrieveClient(Long invoiceId) throws ClientNotFoundException;

    Client storeBahamasClient(Client client);

}
