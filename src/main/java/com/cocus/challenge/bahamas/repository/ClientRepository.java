package com.cocus.challenge.bahamas.repository;

import com.cocus.challenge.bahamas.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client save(Client client);

    Optional<Client> findByCode(Long invoiceId);

}
