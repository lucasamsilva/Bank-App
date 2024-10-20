package com.code_challenge.bank.domain.transaction;

import com.code_challenge.bank.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="creditor_id", updatable = false)
    private User creditor;
    @ManyToOne
    @JoinColumn(name="debtor_id", updatable = false)
    private User debtor;
    @Column(updatable = false)
    private UUID transactionIdentification;
    @Column(updatable = false)
    private BigDecimal amount;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private TransactionStatusEnum status;

}
