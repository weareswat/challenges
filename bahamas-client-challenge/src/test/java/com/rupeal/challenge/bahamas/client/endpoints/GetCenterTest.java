package com.rupeal.challenge.bahamas.client.endpoints;

import com.rupeal.challenge.bahamas.client.model.ClientEntity;
import com.rupeal.challenge.bahamas.client.services.db.InvoiceService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class GetCenterTest {

    @InjectMock
    private InvoiceService invoiceService;

    @Test
    void testRetrieveInvoiceClient() {
        final long invoiceId = 1234L;
        final long fiscalId = 12345L;
        final String clientName = "Jack";
        final String email = "jack.pardal@mail.com";
        when(invoiceService.retrieveInvoiceClients(eq(invoiceId))).thenReturn(Collections.singletonList(new ClientEntity(fiscalId, clientName, email)));

        final GetCenter getCenter = new GetCenter(invoiceService);

        final ClientEntity client = getCenter.getClient(invoiceId);

        assertNotNull(client);
        assertEquals(fiscalId, client.getFiscalId());
        assertEquals(clientName, client.getClientName());
        assertEquals(email, client.getEmail());
    }
}