package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.AccountRepository;
import com.lime.paymybuddy.dao.TransactionRepository;
import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Integer save(Account account) {
        return accountRepository.save(account).getId();
    }

    @Override
    public Optional<Account> findById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account findByUserId(int id) {
        return accountRepository.findByUserId(id);
    }

    @Override
    public Boolean sendMoney(Account fromAcc, Account toAcc, BigDecimal amount) {
        boolean send = false;
        if (fromAcc.getBalance().compareTo(BigDecimal.ONE) > 0
                && fromAcc.getBalance().compareTo(amount) > 0) {
            fromAcc.setBalance(fromAcc.getBalance().subtract(amount));
            accountRepository.save(fromAcc);
            toAcc.setBalance(toAcc.getBalance().add(amount));
            accountRepository.save(toAcc);

            Transaction transaction = new Transaction();
            transaction.setFromAccount(fromAcc);
            transaction.setToAccount(toAcc);
            transaction.setDescription("");
            transaction.setAmount(amount);
            transaction.setTransacted(true);
            transactionRepository.save(transaction);
            System.out.println("Transaction is called!");
            send = true;
        }
        return send;
    }

}
