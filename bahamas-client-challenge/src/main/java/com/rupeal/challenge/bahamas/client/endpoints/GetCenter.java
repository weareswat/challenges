package com.rupeal.challenge.bahamas.client.endpoints;

import com.rupeal.challenge.bahamas.client.model.ClientEntity;
import com.rupeal.challenge.bahamas.client.services.db.InvoiceService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/")
public class GetCenter {

    private final InvoiceService invoiceService;

    @Inject
    public GetCenter(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GET
    @Path("/retrieve-bahamas-client/{invoiceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ClientEntity getClient(@PathParam("invoiceId") Long invoiceId) {

        Collection<ClientEntity> clients = invoiceService.retrieveInvoiceClients(invoiceId);
        if(clients == null || clients.isEmpty()){
            throw new NotFoundException("Invalid invoice Id?");
        }

        return clients.iterator().next();
    }
}