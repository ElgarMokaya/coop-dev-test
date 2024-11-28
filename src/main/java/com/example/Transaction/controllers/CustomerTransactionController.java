package com.example.Transaction.controllers;


import com.example.Transaction.model.CustomerDetails;
import com.example.Transaction.service.CustomerDetailsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
public class CustomerTransactionController {

    private final CustomerDetailsService customerDetailsService;

    public CustomerTransactionController(CustomerDetailsService customerDetailsService) {
        this.customerDetailsService = customerDetailsService;
    }


    @PostMapping("/persist")
    public ResponseEntity<CustomerDetails> persistTransaction(@RequestBody @Valid CustomerDetails transaction) {
        return ResponseEntity.ok(customerDetailsService.persistTransaction(transaction));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestParam String sourceAccount, @RequestParam String destinationAccount, @RequestParam Double amount) {
        return ResponseEntity.ok(customerDetailsService.transferFunds(sourceAccount, destinationAccount, amount));
    }

    @GetMapping("/balance/{account}")
    public ResponseEntity<Double> getBalance(@PathVariable String account) {
        return ResponseEntity.ok(customerDetailsService.getAccountBalance(account));
    }


}
