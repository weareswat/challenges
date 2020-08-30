package com.rupeal.challenge.bahamas.client.endpoints;

import com.rupeal.challenge.bahamas.client.model.ClientEntity;
import com.rupeal.challenge.bahamas.client.services.InvoiceRegistryService;
import com.rupeal.challenge.bahamas.client.services.db.ClientService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PostCenterTest {

    @InjectMock
    ClientService clientService;
    @InjectMock
    InvoiceRegistryService invoiceRegistryService;

    @Test
    public void testPostClient() {
        final long invoiceId = 1234L;
        final long fiscalId = 12345L;
        final String clientName = "Jack";
        final String email = "jack.pardal@mail.com";
        final ClientEntity clientEntity = new ClientEntity(fiscalId, clientName, email);
        when(clientService.createInvoiceClient(eq(invoiceId), eq(fiscalId), eq(clientName), eq(email))).thenReturn(clientEntity);

        final PostCenter postCenter = new PostCenter(clientService, invoiceRegistryService);


        final ClientEntity client = postCenter.postInvoiceClient(invoiceId, fiscalId, clientName, email);
        Assertions.assertNotNull(client);
        Assertions.assertEquals(fiscalId, client.getFiscalId());
        Assertions.assertEquals(clientName, client.getClientName());
        Assertions.assertEquals(email, client.getEmail());

        Mockito.verify(clientService, times(1)).createInvoiceClient(eq(invoiceId), eq(fiscalId), eq(clientName), eq(email));
        Mockito.verify(invoiceRegistryService, times(1)).registerInvoice(eq(invoiceId), eq(clientEntity));
    }

}