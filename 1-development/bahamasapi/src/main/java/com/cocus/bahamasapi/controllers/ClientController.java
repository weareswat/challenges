package com.cocus.bahamasapi.controllers;

import com.cocus.bahamasapi.dtos.ClientDTO;
import com.cocus.bahamasapi.entities.model.Client;
import com.cocus.bahamasapi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/retrieve-bahamas-client/{invoiceId}")
    public ResponseEntity<Client> findByInvoiceId(@PathVariable("invoiceId") Long invoiceId) {
        return new ResponseEntity<>(clientService.getByInvoiceId(invoiceId), HttpStatus.FOUND);
    }

    @PostMapping("/store-bahamas-client/{invoiceId}")
    public ResponseEntity<Client> storeInvoiceClient(@PathVariable("invoiceId") Long invoiceId,
                                                     @Valid @RequestBody ClientDTO clientDTO) {
        clientDTO.setInvoiceId(invoiceId);
        return new ResponseEntity<>(clientService.saveByClientDTO(clientDTO), HttpStatus.CREATED);
    }

}
