package com.cocus.challenge.bahamas.service.impl;


import com.cocus.challenge.bahamas.entities.Client;
import com.cocus.challenge.bahamas.model.ClientRequest;
import com.cocus.challenge.bahamas.repository.ClientRepository;
import com.cocus.challenge.util.ClientTestHelper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DefaultClientServiceIT {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Test insert one to one relationship")
    public void testInsertOneToOne() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ClientRequest request = ClientTestHelper.getBaseClientRequest();

        Client savedClient = clientRepository.save(request.getClient());

        List<Client> allClients = clientRepository.findAll();

        assertNotNull(savedClient);
        assertNotNull(savedClient.getPreferredContact());
        assertNotNull(savedClient.getObservations());
        assertNotNull(savedClient.getSendOptions());
        assertEquals(allClients.size(), 1);
    }



}
