package com.code_challenge.bank.service;

import com.code_challenge.bank.domain.transaction.Transaction;
import com.code_challenge.bank.processor.transaction.TransactionProcessor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionProcessor transactionProcessor;

    @Transactional
    public void processTransaction(Transaction transaction) {
        transactionProcessor.getTransactionProcessor().process(transaction);
    }

}
