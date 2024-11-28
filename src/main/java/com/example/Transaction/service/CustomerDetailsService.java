package com.example.Transaction.service;

import com.example.Transaction.model.CustomerDetails;

public interface CustomerDetailsService {

    CustomerDetails persistTransaction(CustomerDetails transaction);

    String transferFunds(String sourceAccount, String destinationAccount, Double amount);

    Double getAccountBalance(String account);
}
