package com.code_challenge.bank.listener;

import com.code_challenge.bank.domain.transaction.Transaction;
import com.code_challenge.bank.service.NotificationService;
import com.code_challenge.bank.service.TransactionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.code_challenge.bank.configuration.RabbitConfig.NOTIFICATIONS_QUEUE;
import static com.code_challenge.bank.configuration.RabbitConfig.TRANSACTIONS_QUEUE;

@Component
public class Listener {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = TRANSACTIONS_QUEUE)
    void transactionsListener(Transaction transaction) {
        transactionService.processTransaction(transaction);
    }

    @RabbitListener(queues = NOTIFICATIONS_QUEUE)
    void notificate(String email) {
        notificationService.notifyCreditor(email);
    }
}
