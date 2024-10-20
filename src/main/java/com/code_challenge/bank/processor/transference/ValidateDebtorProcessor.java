package com.code_challenge.bank.processor.transference;

import com.code_challenge.bank.domain.account.Account;
import com.code_challenge.bank.domain.user.ClientTypeEnum;
import com.code_challenge.bank.domain.user.User;
import com.code_challenge.bank.processor.Processor;
import com.code_challenge.bank.processor.transference.data.TransferenceData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class ValidateDebtorProcessor extends Processor<TransferenceData, String> {

    @Override
    public String process(TransferenceData data) {
        checkBusinessRule(validateDebtorType(data.getDebitedUser()), "Esse tipo de usuário não pode enviar transferências.");
        checkBusinessRule(validateAccountBalance(data.getDebitedUser().getAccount(), data.getAmount()), "O usuário não tem saldo suficiente!");
        checkBusinessRule(validateAccounts(data.getDebitedUser().getAccount(), data.getCreditedUser().getAccount()), "O usuário não pode enviar transferências para sí próprio");

        return checkNext(data);
    }

    private boolean validateAccounts(Account debtedAccount, Account creditedAccount) {
        return !Objects.equals(debtedAccount.getId(), creditedAccount.getId());
    }

    boolean validateDebtorType(User debitedUser) {
        return debitedUser.sendingMoneyAllowed();
    }

    boolean validateAccountBalance(Account account, BigDecimal transferenceAmount) {
        return account.enoughBalance(transferenceAmount);
    }
}
