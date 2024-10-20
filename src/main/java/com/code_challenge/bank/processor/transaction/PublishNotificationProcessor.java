package com.code_challenge.bank.processor.transaction;

import com.code_challenge.bank.domain.transaction.Transaction;
import com.code_challenge.bank.processor.Processor;
import com.code_challenge.bank.repository.PublisherInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublishNotificationProcessor extends Processor<Transaction, Void> {

    @Autowired
    private PublisherInterface publisherInterface;

    @Override
    public Void process(Transaction transaction) {
         publisherInterface.publishNotification(transaction.getCreditor());

         return null;
    }
}
