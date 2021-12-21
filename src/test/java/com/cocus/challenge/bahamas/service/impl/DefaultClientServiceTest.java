package com.cocus.challenge.bahamas.service.impl;

import com.cocus.challenge.bahamas.exceptions.ClientNotFoundException;
import com.cocus.challenge.bahamas.entities.Client;
import com.cocus.challenge.bahamas.repository.ClientRepository;
import com.cocus.challenge.bahamas.service.BahamasGovernmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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

    @Mock
    private BahamasGovernmentService bahamasGovernmentService;

    @Test
    @DisplayName("Throws 'ClientNotFoundException' if the client doesn't exist in database")
    void whenClientNotFound_thenThrowClientNotFoundException() {
        Mockito.doReturn(Optional.empty()).when(clientRepository).findByCode(Mockito.anyLong());

        ClientNotFoundException clientNotFoundException = Assertions.assertThrows(ClientNotFoundException.class, () -> {
           defaultClientService.retrieveClient(123L);
        });

        assertNotNull(clientNotFoundException);
    }

    @Test
    @DisplayName("Return 'Client' if exists in database")
    void whenClientIsFound_thenReturnClient() {
        Mockito.doReturn(Optional.of(new Client())).when(clientRepository).findByCode(Mockito.anyLong());

        Client client = defaultClientService.retrieveClient(123L);

        assertNotNull(client);
    }
}