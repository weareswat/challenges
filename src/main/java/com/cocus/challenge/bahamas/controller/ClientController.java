package com.cocus.challenge.bahamas.controller;


import com.cocus.challenge.bahamas.entities.Client;
import com.cocus.challenge.bahamas.model.ClientRequest;
import com.cocus.challenge.bahamas.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }


    @GetMapping("/retrieve-bahamas-client/{invoice_id}")
    public ResponseEntity<Client> retrieveClient(@PathVariable("invoice_id") Long invoiceId) {
        return ResponseEntity.ok(clientService.retrieveClient(invoiceId));
    }

    @PostMapping("/store-bahamas-client")
    public ResponseEntity<Client> storeBahamasClient(@RequestBody ClientRequest clientRequest){
        return ResponseEntity.ok(clientService.storeBahamasClient(clientRequest.getClient()));
    }

}
