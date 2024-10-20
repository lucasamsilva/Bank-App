package com.code_challenge.bank.repository.representation.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRepresentation {

    private Long id;
    private UserRepresentation creditor;
    private UserRepresentation debtor;
    private UUID transactionIdentification;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private String status;

}
