package com.udea.lab12026p.service;

import com.udea.lab12026p.dto.TransactionDTO;
import com.udea.lab12026p.dto.TransferRequestDTO;
import com.udea.lab12026p.entity.Customer;
import com.udea.lab12026p.entity.Transaction;
import com.udea.lab12026p.repository.CustomerRepository;
import com.udea.lab12026p.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

     public TransactionDTO transferMoney(TransactionDTO transactionDTO) {
         if (transactionDTO.getSenderAccountNumber() == null || transactionDTO.getReceiverAccountNumber() == null){
             throw new IllegalArgumentException("Sender account number or receiver account number cannot be null");
         }

         Customer sender = customerRepository.findByAccountNumber(transactionDTO.getSenderAccountNumber())
                 .orElseThrow(() -> new IllegalArgumentException("Sender account number not found"));

         Customer receiver = customerRepository.findByAccountNumber(transactionDTO.getReceiverAccountNumber())
                 .orElseThrow(() -> new IllegalArgumentException("Receiver account number not found"));

         if (sender.getAmount() < transactionDTO.getAmount()) {
             throw new IllegalArgumentException("Sender balance not enough");
         }

         sender.setAmount(sender.getAmount() - (transactionDTO.getAmount()));
         receiver.setAmount(receiver.getAmount() + (transactionDTO.getAmount()));

         customerRepository.save(sender);
         customerRepository.save(receiver);

         Transaction transaction = new Transaction();
         transaction.setSenderAccountNumber(sender.getAccountNumber());
         transaction.setReceiverAccountNumber(receiver.getAccountNumber());
         transaction.setAmount(transactionDTO.getAmount());

         Transaction savedTransactionEntity = transactionRepository.save(transaction);

         TransactionDTO savedTransaction = new TransactionDTO();
         savedTransaction.setId(savedTransactionEntity.getId());

         savedTransaction.setSenderAccountNumber(savedTransactionEntity.getSenderAccountNumber());
         savedTransaction.setReceiverAccountNumber(savedTransactionEntity.getReceiverAccountNumber());
         savedTransaction.setAmount(savedTransactionEntity.getAmount());
         return savedTransaction;
     }

     public List<TransactionDTO> getTransactionsForAccount(String accountNumber) {
         List<Transaction> transactions = transactionRepository.findBySenderAccountNumberOrReceiverAccountNumber(accountNumber, accountNumber);
         return transactions.stream().map(transaction -> {TransactionDTO dto = new TransactionDTO();
         dto.setId(transaction.getId());
         dto.setSenderAccountNumber(transaction.getSenderAccountNumber());
         dto.setReceiverAccountNumber(transaction.getReceiverAccountNumber());
         dto.setAmount(transaction.getAmount());
         dto.setTimestamp(transaction.getTimestamp());
         return dto;
         }).collect(Collectors.toList());
     }
}
