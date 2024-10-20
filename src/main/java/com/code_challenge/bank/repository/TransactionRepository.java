package com.code_challenge.bank.repository;

import com.code_challenge.bank.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction getByTransactionIdentification(UUID transactionIdentification);

}
