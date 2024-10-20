package com.code_challenge.bank.repository;

import com.code_challenge.bank.domain.transaction.Transaction;
import com.code_challenge.bank.domain.user.User;

public interface PublisherInterface {

    void publishTransaction(Transaction transaction);

    void publishNotification(User user);

}
