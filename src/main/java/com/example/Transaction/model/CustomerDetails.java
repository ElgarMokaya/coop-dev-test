package com.example.Transaction.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer_details")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDetails {


    public CustomerDetails(String sourceAccount, String destinationAccount, Double amount, String status) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.status = status;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String transactionId;

    @Column(nullable = false)
    @NotEmpty(message = "Source account is required")
    private String sourceAccount;

    @Column(nullable = false)
    @NotEmpty(message = "Destination account is required")
    private String destinationAccount;

    @Column(nullable = false)
    @Positive(message = "Amount should be positive")
    private Double amount;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime transactionDate;



}
