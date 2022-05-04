package com.cocus.bahamasapi.repositories;

import com.cocus.bahamasapi.entities.model.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * Unit tests for the methods in ClientRepository
 * Coverage in 100%
 * Database H2 in memory
 */
@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    void findByInvoiceId_FoundTest() {
        //given
        Long invoiceId = 99L;
        Integer fiscalId = 5;
        String name = "Bob";
        String email = "bob@gmail.com";
        Client client = new Client(invoiceId, fiscalId, name, email);
        clientRepository.save(client);

        //when
        Optional<Client> result = clientRepository.findByInvoiceId(invoiceId);

        //then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getName()).isEqualTo(name);
        assertThat(result.get().getFiscalId()).isEqualTo(fiscalId);
        assertThat(result.get().getEmail()).isEqualTo(email);
    }

    @Test
    void findByInvoiceId_NotFoundTest() {
        //given
        Long invoiceId = 99L;
        Integer fiscalId = 5;
        String name = "Bob";
        String email = "bob@gmail.com";
        Client client = new Client(invoiceId, fiscalId, name, email);
        clientRepository.save(client);

        Long searchInvoiceId = 98L;

        //when
        Optional<Client> result = clientRepository.findByInvoiceId(searchInvoiceId);

        //then
        assertThat(result.isEmpty()).isTrue();
    }
}