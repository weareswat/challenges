package com.cocus.challenge.bahamas.repository;

import com.cocus.challenge.bahamas.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Client save(Client client);

    Optional<Client> findById(String invoiceId);

}
