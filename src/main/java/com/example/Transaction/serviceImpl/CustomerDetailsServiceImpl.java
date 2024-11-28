package com.example.Transaction.serviceImpl;

import com.example.Transaction.model.CustomerDetails;
import com.example.Transaction.repositories.CustomerDetailsRepository;
import com.example.Transaction.service.CustomerDetailsService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CustomerDetailsServiceImpl implements CustomerDetailsService {

    private final CustomerDetailsRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CustomerDetailsServiceImpl(CustomerDetailsRepository repository, KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public CustomerDetails persistTransaction(CustomerDetails transaction) {
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("SUCCESS");
        kafkaTemplate.send("transaction_topic", "New transaction: " + transaction.getTransactionId());
        return repository.save(transaction);
    }

@Override
public String transferFunds(String sourceAccount, String destinationAccount, Double amount) {
    // Find the current balance of the source account
    Double sourceBalance = repository.findAccountBalance(sourceAccount);
    if (sourceBalance == null || sourceBalance < amount) {
        return "Insufficient funds"; // Handle insufficient funds
    }

    // Assuming the source account has sufficient funds, perform the transfer
    // First, reduce the balance of the source account
    String sourceTransactionId = UUID.randomUUID().toString(); // Generate a unique transaction ID for this update
    repository.updateAccountBalance(sourceAccount, sourceBalance - amount, sourceTransactionId); // Update source account balance

    // Find and update the destination account balance
    Double destinationBalance = repository.findAccountBalance(destinationAccount);
    String destinationTransactionId = UUID.randomUUID().toString(); // Generate a unique transaction ID for the destination
    repository.updateAccountBalance(destinationAccount, destinationBalance + amount, destinationTransactionId); // Update destination account balance

    // Persist the transaction details with "PENDING" status
    persistTransaction(new CustomerDetails(sourceAccount, destinationAccount, amount, "PENDING"));

    return "Transfer initiated"; // Indicate that the transfer has been initiated
}


    @Override
    public Double getAccountBalance(String account) {
        return repository.findAccountBalance(account);
    }
}
