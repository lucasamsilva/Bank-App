package com.code_challenge.bank.repository;

import com.code_challenge.bank.domain.user.User;

import java.math.BigDecimal;

public interface NotifyRepository {

    void notify(User user, BigDecimal amount);

}
