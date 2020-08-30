package com.rupeal.challenge.bahamas.client.endpoints;

import com.rupeal.challenge.bahamas.client.model.ClientEntity;
import com.rupeal.challenge.bahamas.client.services.db.ClientService;
import com.rupeal.challenge.bahamas.client.services.InvoiceRegistryService;

import javax.inject.Inject;
import javax.validation.constraints.Email;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/")
public class PostCenter {

    private final ClientService clientService;

    private final InvoiceRegistryService invoiceRegistryService;

    @Inject
    public PostCenter(final ClientService clientService, final InvoiceRegistryService invoiceRegistryService) {
        this.clientService = clientService;
        this.invoiceRegistryService = invoiceRegistryService;
    }

    @POST
    @Path("/store-bahamas-client/{invoiceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ClientEntity postInvoiceClient(@PathParam("invoiceId") Long invoiceId,
                             @QueryParam("fiscal_id") Long fiscalId,
                             @QueryParam("name") String clientName,
                             @Email @QueryParam("email") String email) {

        final ClientEntity invoiceClient = clientService.createInvoiceClient(invoiceId, fiscalId, clientName, email);
        if(invoiceClient != null){
            invoiceRegistryService.registerInvoice(invoiceId, invoiceClient);
        }

        return invoiceClient;
    }
}