package com.cocus.challenge.bahamas.service.impl;

import com.cocus.challenge.bahamas.exceptions.ClientNotFoundException;
import com.cocus.challenge.bahamas.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DefaultClientServiceTest {

    @InjectMocks
    private DefaultClientService defaultClientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    void whenClientNotFound_thenThrowClientNotFoundException() {
        Mockito.doReturn(Optional.empty()).when(clientRepository).findById(Mockito.anyString());

        ClientNotFoundException clientNotFoundException = Assertions.assertThrows(ClientNotFoundException.class, () -> {
           defaultClientService.retrieveClient("123");
        });

        assertNotNull(clientNotFoundException);
    }

    @Test
    void storeBahamasClient() {
        fail();
    }
}