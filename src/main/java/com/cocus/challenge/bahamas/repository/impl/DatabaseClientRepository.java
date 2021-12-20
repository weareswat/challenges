package com.cocus.challenge.bahamas.repository.impl;


import com.cocus.challenge.bahamas.model.Client;
import com.cocus.challenge.bahamas.repository.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DatabaseClientRepository implements ClientRepository {

    /*TODO
    * connect to database
    * create docker-compose file
    *
    * */

    @Override
    public Client save(Client client) {
        return null;
    }

    @Override
    public Optional<Client> find(Long invoiceId) {
        return Optional.empty();
//        return Optional.of(new Client(1234L));
    }
}
