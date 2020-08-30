package com.rupeal.challenge.bahamas.client.services;

import com.rupeal.challenge.bahamas.client.model.ClientEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Mocked external invoice client registry class. Only Logs possible request.
 */
@ApplicationScoped
public class InvoiceRegistryService {
    private static final Logger LOG = LoggerFactory.getLogger(InvoiceRegistryService.class);

    private final Client client;
    private final InvoiceRegistryProperties properties;

    public InvoiceRegistryService() {
        this(new InvoiceRegistryProperties());
    }

    @Inject
    public InvoiceRegistryService(final InvoiceRegistryProperties properties) {
        this.properties = properties;
        ExecutorService executorService = Executors.newCachedThreadPool();
        client = ClientBuilder.newBuilder().executorService(executorService).build();
    }


    public void registerInvoice(final Long invoiceId, final ClientEntity invoiceClient) {
        LOG.info("Submitting client registry to {}{}", properties.getBaseUrl(), buildParameterString(invoiceId, invoiceClient));

        //Example code for the actual request if it was not a mock.
        /*final Response response = client.target(properties.getBaseUrl())
                .queryParam("invoice", invoiceId)
                .queryParam("fiscal_id", invoiceClient.getFiscalId())
                .queryParam("name", invoiceClient.getClientName())
                .queryParam("email", invoiceClient.getEmail())
                .request()
                .post(null);

        if(response.getStatusInfo() == Response.Status.ACCEPTED || response.getStatusInfo() == Response.Status.OK){
            LOG.info("Registry successful");
        } else {
            LOG.error("Failed to register new client");
        }*/
    }

    private String buildParameterString(final Long invoiceId, final ClientEntity invoiceClient){
        return new StringBuilder("?")
                .append("invoice=").append(invoiceId)
                .append("&fiscal_id=").append(invoiceClient.getFiscalId())
                .append("&name=").append(invoiceClient.getClientName())
                .append("&email=").append(invoiceClient.getEmail())
                .toString();
    }
}
