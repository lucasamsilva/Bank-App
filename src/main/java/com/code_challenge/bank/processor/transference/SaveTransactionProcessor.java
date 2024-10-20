package com.code_challenge.bank.processor.transference;

import com.code_challenge.bank.domain.transaction.Transaction;
import com.code_challenge.bank.domain.transaction.TransactionStatusEnum;
import com.code_challenge.bank.processor.Processor;
import com.code_challenge.bank.processor.transference.data.TransferenceData;
import com.code_challenge.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SaveTransactionProcessor extends Processor<TransferenceData, String> {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public String process(TransferenceData data) {
        var transaction = Transaction.builder()
            .status(TransactionStatusEnum.PROCESSING)
            .debtor(data.getDebitedUser())
            .creditor(data.getCreditedUser())
            .amount(data.getAmount())
            .transactionIdentification(UUID.randomUUID())
            .build();

        data.setTransaction(transactionRepository.save(transaction));

        return checkNext(data);
    }
}
