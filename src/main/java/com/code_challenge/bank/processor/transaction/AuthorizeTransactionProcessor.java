package com.code_challenge.bank.processor.transaction;

import com.code_challenge.bank.domain.account.Account;
import com.code_challenge.bank.domain.transaction.Transaction;
import com.code_challenge.bank.domain.transaction.TransactionStatusEnum;
import com.code_challenge.bank.exception.BusinessException;
import com.code_challenge.bank.processor.Processor;
import com.code_challenge.bank.repository.AccountRepository;
import com.code_challenge.bank.repository.ToolsRepository;
import com.code_challenge.bank.repository.TransactionRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AuthorizeTransactionProcessor extends Processor<Transaction, Void> {

    @Autowired
    private ToolsRepository toolsRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Void process(Transaction transactionData) {
        var transaction = transactionRepository.getReferenceById(transactionData.getId());
        try {
            toolsRepository.getAuthorization();
            saveTransactionWithStatus(transaction, TransactionStatusEnum.ACCEPTED);
            transferMoney(transaction.getDebtor().getAccount(), transaction.getCreditor().getAccount(), transaction.getAmount());

            checkNext(transaction);

            return null;
        } catch (FeignException.Forbidden | BusinessException ex) {
            saveTransactionWithStatus(transaction, TransactionStatusEnum.REFUSED);

            return null;
        }
    }

    private void transferMoney(Account debitedAccount, Account creditedAccount, BigDecimal amount) {
        debitedAccount.debit(amount);
        creditedAccount.credit(amount);

        accountRepository.saveAll(List.of(debitedAccount, creditedAccount));
    }

    private void saveTransactionWithStatus(Transaction transaction, TransactionStatusEnum status) {
        transaction.setStatus(status);
        transactionRepository.save(transaction);
    }

}
