package com.cocus.bahamasapi.services;

import com.cocus.bahamasapi.dtos.ClientDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BahamasGovService {

    /**
     * Method for mocking call to bahamas gov register.
     */
    public ResponseEntity<String> mockCall(ClientDTO clientDTO) {

        String url = "https://bahamas.gov/register?invoice=%s&fiscal_id=%s&name=%s&email=%s";
        String request = String.format(url, clientDTO.getInvoiceId(),
                clientDTO.getFiscalId(),
                clientDTO.getName(),
                clientDTO.getEmail());

        return new ResponseEntity(request, HttpStatus.CREATED);
    }
}
