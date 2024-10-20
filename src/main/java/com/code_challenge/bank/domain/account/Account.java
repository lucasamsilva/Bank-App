package com.code_challenge.bank.domain.account;

import com.code_challenge.bank.domain.user.User;
import com.code_challenge.bank.exception.BusinessException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@Table(name="accounts")
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String number;

    @Column
    private String branch;

    @Column
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public boolean debit(BigDecimal amount) {
        if(!enoughBalance(amount)) {
            throw new BusinessException("Saldo Insuficiente");
        }
        this.balance = this.balance.subtract(amount);
        return true;
    }

    public boolean credit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return true;
    }

    public boolean enoughBalance(BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }
}
