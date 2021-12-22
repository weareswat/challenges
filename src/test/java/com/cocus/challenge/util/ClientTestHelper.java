package com.cocus.challenge.util;

import com.cocus.challenge.bahamas.entities.Client;
import com.cocus.challenge.bahamas.entities.PreferredContact;
import com.cocus.challenge.bahamas.enums.AcceptedLanguages;
import com.cocus.challenge.bahamas.model.ClientRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientTestHelper {

    public static ClientRequest getBaseClientRequest(){
        ClientRequest clientRequest = new ClientRequest();
        Client client = new Client();
        PreferredContact contact = new PreferredContact();

        client.setName("Client Name");
        client.setCode(12345L);
        client.setEmail("foo@bar.com");
        client.setLanguage(AcceptedLanguages.PT);
        client.setAddress("Avenida da República, Lisboa");
        client.setCity("Lisboa");
        client.setPostalCode("1050-555");
        client.setCountry("Portugal");
        client.setFiscalId("508025338");
        client.setWebsite("www.invoicexpress.com");
        client.setPhone("213456789");
        client.setFax("213456788");
        client.setObservations("Observations");
        client.setSendOptions("1");

        contact.setName(client.getName());
        contact.setEmail(client.getEmail());
        contact.setPhone(client.getPhone());

        client.setPreferredContact(contact);

        clientRequest.setClient(client);

        return clientRequest;
    }


    private String getBaseClientRequestAsJsonString(){
        try {
            return new ObjectMapper().writeValueAsString(getBaseClientRequest());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
