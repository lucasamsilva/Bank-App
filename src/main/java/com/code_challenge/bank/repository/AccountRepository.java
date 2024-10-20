package com.code_challenge.bank.repository;

import com.code_challenge.bank.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account getByUserId(Long userId);

}
