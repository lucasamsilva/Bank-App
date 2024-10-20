package com.code_challenge.bank.processor.transaction;

import com.code_challenge.bank.domain.transaction.Transaction;
import com.code_challenge.bank.processor.Processor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TransactionProcessor {

    private Processor<Transaction, Void> transactionProcessor;

    public TransactionProcessor(AuthorizeTransactionProcessor authorizeTransactionProcessor, PublishNotificationProcessor publishNotificationProcessor) {
        transactionProcessor = Processor.link(authorizeTransactionProcessor, publishNotificationProcessor);
    }

}
