package com.cocus.bahamasapi.services;

import com.cocus.bahamasapi.dtos.ClientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Unit tests for the methods in BahamasGovService
 * Coverage in 100%
 */
class BahamasGovServiceTest {

    @Autowired
    private BahamasGovService underTest;

    @BeforeEach
    void setUp() {
        underTest = new BahamasGovService();
    }

    @Test
    void mockCall_test() {
        //given
        Long invoiceId = 99L;
        Integer fiscalId = 5;
        String name = "Bob";
        String email = "bob@gmail.com";
        ClientDTO clientDTO = new ClientDTO(invoiceId, fiscalId, name, email);

        //when
        ResponseEntity<String> response = underTest.mockCall(clientDTO);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo("https://bahamas.gov/register?invoice=99&fiscal_id=5&name=Bob&email=bob@gmail.com");
    }
}