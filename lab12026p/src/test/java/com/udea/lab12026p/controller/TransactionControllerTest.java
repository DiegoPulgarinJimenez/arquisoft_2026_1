package com.udea.lab12026p.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.lab12026p.dto.TransactionDTO;
import com.udea.lab12026p.entity.Customer;
import com.udea.lab12026p.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/transactions";
    }

    @Test
    void transferMoney_endpoint_success() {

        // Crear clientes
        Customer sender = new Customer();
        sender.setFirstName("Test");
        sender.setLastName("Sender");
        sender.setAccountNumber("555");
        sender.setAmount(500.0);

        Customer receiver = new Customer();
        receiver.setFirstName("Test");
        receiver.setLastName("Receiver");
        receiver.setAccountNumber("666");
        receiver.setAmount(100.0);

        customerRepository.save(sender);
        customerRepository.save(receiver);

        // DTO
        TransactionDTO dto = new TransactionDTO();
        dto.setSenderAccountNumber("555");
        dto.setReceiverAccountNumber("666");
        dto.setAmount(100.0);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TransactionDTO> request = new HttpEntity<>(dto, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                getBaseUrl(),
                request,
                String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("555"));
    }

    @Test
    void getTransactions_endpoint() {

        ResponseEntity<String> response = restTemplate.getForEntity(
                getBaseUrl() + "/555",
                String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}