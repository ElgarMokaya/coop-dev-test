package com.example.Transaction.repositories;

import com.example.Transaction.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailsRepository  extends JpaRepository<CustomerDetails, Integer> {
    @Query("SELECT SUM(amount) FROM CustomerDetails WHERE sourceAccount = :account")
    Double findAccountBalance(@Param("account") String account);
@Modifying
@Query("UPDATE CustomerDetails SET sourceAccount = :account, amount = :balance WHERE transactionId = :transactionId")
void updateAccountBalance(@Param("account") String account, @Param("balance") Double balance, @Param("transactionId") String transactionId);




}
