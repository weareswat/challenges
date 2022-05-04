package com.cocus.bahamasapi.services;

import com.cocus.bahamasapi.dtos.ClientDTO;
import com.cocus.bahamasapi.entities.model.Client;
import com.cocus.bahamasapi.handler.exceptions.BahamasGovIntegrationException;
import com.cocus.bahamasapi.handler.exceptions.RecordConflictingException;
import com.cocus.bahamasapi.handler.exceptions.RecordNotFoundException;
import com.cocus.bahamasapi.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final BahamasGovService bahamasGovService;

    @Autowired
    public ClientService(ClientRepository clientRepository, BahamasGovService bahamasGovService) {
        this.clientRepository = clientRepository;
        this.bahamasGovService = bahamasGovService;
    }

    /**
     * Find a existing Client by invoice Id
     */
    public Client getByInvoiceId(Long invoiceId) {
        Optional<Client> result = clientRepository.findByInvoiceId(invoiceId);
        if (result.isEmpty()) {
            throw new RecordNotFoundException("Invalid invoice id: " + invoiceId);
        }
        return result.get();
    }

    /**
     * Save a new client by client DTO after register in bahamas gov
     */
    public Client saveByClientDTO(ClientDTO clientDTO) {
        // Verify if invoice id already exists
        if (clientRepository.findByInvoiceId(clientDTO.getInvoiceId()).isPresent()) {
            throw new RecordConflictingException("Already exists a client with invoice id " + clientDTO.getInvoiceId());
        }

        // Make call to Bahamas Gov
        ResponseEntity<String> response = bahamasGovService.mockCall(clientDTO);
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            throw new BahamasGovIntegrationException("Error calling bahamas gov to register client with invoice id " + clientDTO.getInvoiceId());
        }

        // Save the new client
        Client newClient = new Client(clientDTO.getInvoiceId(), clientDTO.getFiscalId(), clientDTO.getName(), clientDTO.getEmail());
        return clientRepository.save(newClient);
    }
}
