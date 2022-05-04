package com.cocus.bahamasapi.controllers;

import com.cocus.bahamasapi.dtos.ClientDTO;
import com.cocus.bahamasapi.entities.model.Client;
import com.cocus.bahamasapi.handler.exceptions.RecordConflictingException;
import com.cocus.bahamasapi.handler.exceptions.RecordNotFoundException;
import com.cocus.bahamasapi.repositories.ClientRepository;
import com.cocus.bahamasapi.services.BahamasGovService;
import com.cocus.bahamasapi.services.ClientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

/**
 * Integration tests for endpoints in ClientController
 * Coverage in 100%
 * Database H2 in memory
 * No mocking methods
 */
@DataJpaTest
class ClientControllerTest {

    private ClientController underTest;

    private ClientService clientService;

    private BahamasGovService bahamasGovService;

    @Autowired
    private ClientRepository clientRepository;

    private final Long INVOICE_ID_1 = 1L;
    private final Integer FISCAL_ID_1 = 1;
    private final String NAME_1 = "Mariam";
    private final String EMAIL_1 = "mariam@gmail.com";

    private final Long INVOICE_ID_2 = 2L;
    private final Integer FISCAL_ID_2 = 2;
    private final String NAME_2 = "Alex";
    private final String EMAIL_2 = "alex@gmail.com";

    @BeforeEach
    void setUp() {
        bahamasGovService = new BahamasGovService();
        clientService = new ClientService(clientRepository, bahamasGovService);
        underTest = new ClientController(clientService);
        createScenario();
    }

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }

    private void createScenario() {
        Client mariam = new Client(INVOICE_ID_1, FISCAL_ID_1, NAME_1, EMAIL_1);
        clientRepository.saveAll(List.of(mariam));
    }

    @Test
    void findByInvoiceId_SuccessTest() {
        //given scenario
        //when
        ResponseEntity<Client> response = underTest.findByInvoiceId(INVOICE_ID_1);
        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getBody().getInvoiceId()).isEqualTo(INVOICE_ID_1);
        assertThat(response.getBody().getFiscalId()).isEqualTo(FISCAL_ID_1);
        assertThat(response.getBody().getName()).isEqualTo(NAME_1);
        assertThat(response.getBody().getEmail()).isEqualTo(EMAIL_1);
    }

    @Test
    void findByInvoiceId_NotFoundTest() {
        //given scenario
        //when then
        assertThatThrownBy(() -> underTest.findByInvoiceId(INVOICE_ID_2))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Invalid invoice id: " + INVOICE_ID_2);
    }

    @Test
    void storeInvoiceClient_SuccessTest() {
        //given scenario and
        ClientDTO newClientDTO = new ClientDTO(FISCAL_ID_2, NAME_2, EMAIL_2);

        //when
        ResponseEntity<Client> response = underTest.storeInvoiceClient(INVOICE_ID_2, newClientDTO);

        //then
        Client clientCreated = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(clientCreated.getInvoiceId()).isEqualTo(INVOICE_ID_2);
        assertThat(clientCreated.getFiscalId()).isEqualTo(FISCAL_ID_2);
        assertThat(clientCreated.getName()).isEqualTo(NAME_2);
        assertThat(clientCreated.getEmail()).isEqualTo(EMAIL_2);

        Client clientFound = clientRepository.findByInvoiceId(INVOICE_ID_2).get();
        assertThat(clientFound).usingRecursiveComparison()
                .isEqualTo(clientCreated);
    }

    @Test
    void storeInvoiceClient_ConflictingExceptionTest() {
        //given scenario and
        ClientDTO newClientDTO = new ClientDTO(FISCAL_ID_1, NAME_1, EMAIL_1);

        //when then
        assertThatThrownBy(() -> underTest.storeInvoiceClient(INVOICE_ID_1, newClientDTO))
                .isInstanceOf(RecordConflictingException.class)
                .hasMessageContaining("Already exists a client with invoice id " + INVOICE_ID_1);
    }
}