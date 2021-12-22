package com.cocus.challenge.bahamas.service.impl;

import com.cocus.challenge.bahamas.exceptions.ClientAlreadyExistsException;
import com.cocus.challenge.bahamas.exceptions.ClientNotFoundException;
import com.cocus.challenge.bahamas.entities.Client;
import com.cocus.challenge.bahamas.repository.ClientRepository;
import com.cocus.challenge.bahamas.service.BahamasGovernmentService;
import com.cocus.challenge.util.ClientTestHelper;
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

    @Test
    @DisplayName("Throw an ClientAlreadyExistsException if the client already exists during insert")
    void whenClientExistsOnSave_thenThrowClientAlreadyExistsException() {
        Mockito.doReturn(Optional.of(new Client())).when(clientRepository).findByCode(Mockito.anyLong());

        Client client = ClientTestHelper.getBaseClientRequest().getClient();

        ClientAlreadyExistsException clientAlreadyExistsException = Assertions.assertThrows(ClientAlreadyExistsException.class, () -> {
            defaultClientService.storeBahamasClient(client);
        });

        assertNotNull(clientAlreadyExistsException);
    }

    @Test
    @DisplayName("Save client if does not exist on database")
    void whenClientDoesNotExistsOnSave_thenSaveIt() {
        Mockito.doReturn(Optional.empty()).when(clientRepository).findByCode(Mockito.anyLong());

        Client client = ClientTestHelper.getBaseClientRequest().getClient();

        defaultClientService.storeBahamasClient(client);
    }
}