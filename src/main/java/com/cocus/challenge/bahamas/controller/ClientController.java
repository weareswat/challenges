package com.cocus.challenge.bahamas.controller;


import com.cocus.challenge.bahamas.exceptions.ClientNotFoundException;
import com.cocus.challenge.bahamas.model.Client;
import com.cocus.challenge.bahamas.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }


    @GetMapping(value = "/retrieve-bahamas-client/{invoice_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> retrieveClient(@PathVariable("invoice_id") Long invoiceId) throws ClientNotFoundException {
        return ResponseEntity.ok(clientService.retrieveClient(invoiceId));
    }

}
