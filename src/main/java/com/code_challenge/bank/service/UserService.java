package com.code_challenge.bank.service;

import com.code_challenge.bank.domain.account.Account;
import com.code_challenge.bank.domain.user.User;
import com.code_challenge.bank.exception.AuthException;
import com.code_challenge.bank.repository.AccountRepository;
import com.code_challenge.bank.repository.UserRepository;
import com.code_challenge.bank.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AccountRepository accountRepository;

    public void registerUser(User user) {
        user = userRepository.save(user);

        var account = Account.builder()
                .balance(BigDecimal.ZERO)
                .number(UUID.randomUUID().toString())
                .branch("0001")
                .user(user)
                .build();

        accountRepository.save(account);
    }

    public String login(User user) {
        var userDetails = Optional
                .ofNullable(userDetailsService.loadUserByUsername(user.getUsername()))
                .orElseThrow(AuthException::new);
        var validPassword = passwordEncoder.matches(user.getPassword(), userDetails.getPassword());
        if(!validPassword) {
            throw new AuthException();
        }
        return jwtUtil.generateToken(userDetails);
    }
}
