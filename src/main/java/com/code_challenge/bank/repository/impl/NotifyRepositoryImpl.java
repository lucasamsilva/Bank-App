package com.code_challenge.bank.repository.impl;

import com.code_challenge.bank.domain.user.User;
import com.code_challenge.bank.repository.NotifyRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class NotifyRepositoryImpl implements NotifyRepository {

    @Override
    public void notify(User user, BigDecimal amount) {
        // TODO
    }
}
