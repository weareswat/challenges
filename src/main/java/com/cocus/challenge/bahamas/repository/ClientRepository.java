package com.cocus.challenge.bahamas.repository;

import com.cocus.challenge.bahamas.model.Client;

import java.util.Optional;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> find(Long invoiceId);

}
