package com.udea.lab12026p.service;

import com.udea.lab12026p.dto.TransactionDTO;
import com.udea.lab12026p.entity.Customer;
import com.udea.lab12026p.repository.CustomerRepository;
import com.udea.lab12026p.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void transferMoney_success() {

        // Crear clientes de prueba
        Customer sender = new Customer();
        sender.setFirstName("Diego");
        sender.setLastName("Sender");
        sender.setAccountNumber("111");
        sender.setAmount(500.0);

        Customer receiver = new Customer();
        receiver.setFirstName("Juan");
        receiver.setLastName("Receiver");
        receiver.setAccountNumber("222");
        receiver.setAmount(100.0);

        customerRepository.save(sender);
        customerRepository.save(receiver);

        // Crear DTO
        TransactionDTO dto = new TransactionDTO();
        dto.setSenderAccountNumber("111");
        dto.setReceiverAccountNumber("222");
        dto.setAmount(200.0);

        // Ejecutar
        TransactionDTO result = transactionService.transferMoney(dto);

        // Validaciones
        assertNotNull(result.getId());
        assertEquals(200.0, result.getAmount());

        Customer updatedSender = customerRepository.findByAccountNumber("111").get();
        Customer updatedReceiver = customerRepository.findByAccountNumber("222").get();

        assertEquals(300.0, updatedSender.getAmount());
        assertEquals(300.0, updatedReceiver.getAmount());
    }

    @Test
    void transferMoney_insufficientBalance() {

        Customer sender = new Customer();
        sender.setFirstName("Low");
        sender.setLastName("Money");
        sender.setAccountNumber("333");
        sender.setAmount(50.0);

        Customer receiver = new Customer();
        receiver.setFirstName("Rich");
        receiver.setLastName("Guy");
        receiver.setAccountNumber("444");
        receiver.setAmount(100.0);

        customerRepository.save(sender);
        customerRepository.save(receiver);

        TransactionDTO dto = new TransactionDTO();
        dto.setSenderAccountNumber("333");
        dto.setReceiverAccountNumber("444");
        dto.setAmount(200.0);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transactionService.transferMoney(dto);
        });

        assertTrue(exception.getMessage().contains("Sender balance not enough"));
    }

    @Test
    void getTransactionsForAccount() {

        List<TransactionDTO> transactions = transactionService.getTransactionsForAccount("111");

        assertNotNull(transactions);
    }
}