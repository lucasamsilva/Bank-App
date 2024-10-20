package com.code_challenge.bank.service;

import com.code_challenge.bank.domain.transaction.Transaction;
import com.code_challenge.bank.domain.user.User;
import com.code_challenge.bank.processor.transference.data.TransferenceData;
import com.code_challenge.bank.processor.transference.TransferenceProcessor;
import com.code_challenge.bank.repository.TransactionRepository;
import com.code_challenge.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
public class TransferenceService {

    @Autowired
    private TransferenceProcessor transferenceProcessor;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public String doTransference(Long creditedUserId, BigDecimal amount) {
        var loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var creditedUser = userRepository.getReferenceById(creditedUserId);
        var transferenceData = new TransferenceData(loggedUser, amount, creditedUser, null);

        return transferenceProcessor.getProcessor().process(transferenceData);
    }

    public Transaction getByTransactionIdentification(UUID transactionId) {
        return transactionRepository.getByTransactionIdentification(transactionId);
    }
}
