package com.code_challenge.bank.processor.transference.data;

import com.code_challenge.bank.domain.transaction.Transaction;
import com.code_challenge.bank.domain.user.User;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferenceData {

    private User debitedUser;
    private BigDecimal amount;
    private User creditedUser;
    private Transaction transaction;

}
