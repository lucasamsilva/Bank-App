package com.code_challenge.bank.repository.impl;

import com.code_challenge.bank.domain.transaction.Transaction;
import com.code_challenge.bank.domain.user.User;
import com.code_challenge.bank.mapper.TransactionMapper;
import com.code_challenge.bank.repository.PublisherInterface;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.code_challenge.bank.configuration.RabbitConfig.*;

@Repository
public class PublisherInterfaceImpl implements PublisherInterface {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public void publishTransaction(Transaction transaction) {
        amqpTemplate.convertAndSend(TRANSACTIONS_EXCHANGE, TRANSACTIONS_ROUTING_KEY, transactionMapper.toRepresentation(transaction));
    }

    @Override
    public void publishNotification(User user) {
        amqpTemplate.convertAndSend(NOTIFICATIONS_EXCHANGE, NOTIFICATIONS_ROUTING_KEY, user.getEmail());
    }
}
