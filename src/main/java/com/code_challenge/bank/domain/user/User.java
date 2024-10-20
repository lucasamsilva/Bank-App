package com.code_challenge.bank.domain.user;

import com.code_challenge.bank.domain.account.Account;
import com.code_challenge.bank.domain.transaction.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String document;
    private String name;
    private boolean enabled;
    private boolean accountNonLocked;
    @Enumerated(EnumType.STRING)
    private ClientTypeEnum accountType;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Account account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    public boolean sendingMoneyAllowed() {
        return ClientTypeEnum.NATURAL_PERSON.equals(this.accountType);
    }

}
