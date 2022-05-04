package com.cocus.bahamasapi.services;

import com.cocus.bahamasapi.dtos.ClientDTO;
import com.cocus.bahamasapi.entities.model.Client;
import com.cocus.bahamasapi.handler.exceptions.BahamasGovIntegrationException;
import com.cocus.bahamasapi.handler.exceptions.RecordConflictingException;
import com.cocus.bahamasapi.handler.exceptions.RecordNotFoundException;
import com.cocus.bahamasapi.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


/**
 * Unit tests for the methods in ClientService
 * Coverage in 100%
 * Mocks for repository and other service
 */
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    private ClientService underTest;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private BahamasGovService bahamasGovService;

    @BeforeEach
    void setUp() {
        underTest = new ClientService(clientRepository, bahamasGovService);
    }

    @Test
    void getByInvoiceId_RecordNotFoundExceptionTest() {
        //when
        when(clientRepository.findByInvoiceId(anyLong())).thenReturn(Optional.empty()); //mock

        //then
        Exception exception = assertThrows(RecordNotFoundException.class, () -> {
            underTest.getByInvoiceId(1L);
        });

        String expectedMessage = "Invalid invoice id: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getByInvoiceId_SuccessTest() {
        //given
        Long invoiceId = 99L;
        Integer fiscalId = 5;
        String name = "Bob";
        String email = "bob@gmail.com";
        Client client = new Client(invoiceId, fiscalId, name, email);

        //when
        when(clientRepository.findByInvoiceId(invoiceId)).thenReturn(Optional.of(client)); //mock
        Client result = underTest.getByInvoiceId(invoiceId);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getFiscalId()).isEqualTo(fiscalId);
        assertThat(result.getEmail()).isEqualTo(email);
    }

    @Test
    void saveByClientDTO_SuccessTest() {
        //given
        Long invoiceId = 99L;
        Integer fiscalId = 5;
        String name = "Bob";
        String email = "bob@gmail.com";
        ClientDTO clientDTO = new ClientDTO(invoiceId, fiscalId, name, email);

        //when
        when(clientRepository.findByInvoiceId(any())).thenReturn(Optional.empty()); //mock
        when(bahamasGovService.mockCall(any())).thenReturn(ResponseEntity.status(HttpStatus.CREATED).build()); //mock
        underTest.saveByClientDTO(clientDTO);

        //then
        ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).save(clientArgumentCaptor.capture());
        Client capturedClient = clientArgumentCaptor.getValue();

        assertThat(capturedClient.getInvoiceId()).isEqualTo(clientDTO.getInvoiceId());
        assertThat(capturedClient.getName()).isEqualTo(clientDTO.getName());
        assertThat(capturedClient.getFiscalId()).isEqualTo(clientDTO.getFiscalId());
        assertThat(capturedClient.getEmail()).isEqualTo(clientDTO.getEmail());
    }

    @Test
    void saveByClientDTO_ConflictingExceptionTest() {
        //given
        Long invoiceId = 99L;
        Integer fiscalId = 5;
        String name = "Bob";
        String email = "bob@gmail.com";
        ClientDTO clientDTO = new ClientDTO(invoiceId, fiscalId, name, email);

        //when
        when(clientRepository.findByInvoiceId(any())).thenReturn(Optional.of(new Client())); //mock

        //then
        assertThatThrownBy(() -> underTest.saveByClientDTO(clientDTO))
                .isInstanceOf(RecordConflictingException.class)
                .hasMessageContaining("Already exists a client with invoice id " + clientDTO.getInvoiceId());
        verify(clientRepository, never()).save(any());
    }

    @Test
    void saveByClientDTO_BahamasIntegrationExceptionTest() {
        //given
        Long invoiceId = 99L;
        Integer fiscalId = 5;
        String name = "Bob";
        String email = "bob@gmail.com";
        ClientDTO clientDTO = new ClientDTO(invoiceId, fiscalId, name, email);

        //when
        when(clientRepository.findByInvoiceId(any())).thenReturn(Optional.empty()); //mock
        when(bahamasGovService.mockCall(any())).thenReturn(new ResponseEntity(any(), HttpStatus.INTERNAL_SERVER_ERROR)); //mock

        //then
        assertThatThrownBy(() -> underTest.saveByClientDTO(clientDTO))
                .isInstanceOf(BahamasGovIntegrationException.class)
                .hasMessageContaining("Error calling bahamas gov to register client with invoice id " + clientDTO.getInvoiceId());
        verify(clientRepository, never()).save(any());
    }
}