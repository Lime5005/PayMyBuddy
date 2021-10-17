package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.AccountRepository;
import com.lime.paymybuddy.dao.TransactionRepository;
import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.DaoUser;
import com.lime.paymybuddy.model.Transaction;
import com.lime.paymybuddy.model.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean saveOrUpdate(AccountDto accountDto, String email) {
        DaoUser user = userRepository.findByEmail(email);
        Account account = accountRepository.findByUserId(user.getId());
        if (account == null) {
            Account newAccount = new Account();
            newAccount.setUser(user);
            newAccount.setBalance(accountDto.getBalance());
            accountRepository.save(newAccount);
        } else {
            accountRepository.update(account.getId(), accountDto.getBalance());
        }
        return true;
    }

    @Override
    public Account findByUserId(int id) {
        return accountRepository.findByUserId(id);
    }

    @Override
    public Boolean sendMoney(Account fromAcc, Account toAcc, BigDecimal amount, String description) {
        boolean send = false;
        if (fromAcc == null || toAcc == null) {
            return false;
        } else {
            if (fromAcc.getBalance().compareTo(BigDecimal.ONE) > 0
                    && fromAcc.getBalance().compareTo(amount) > 0) {
                // 1, Transfer:
                fromAcc.setBalance(fromAcc.getBalance().subtract(amount.add(new BigDecimal("0.005").multiply(amount))));
                accountRepository.save(fromAcc);
                toAcc.setBalance(toAcc.getBalance().add(amount));
                accountRepository.save(toAcc);

                // 2, Save a record in app:
                Transaction transaction = new Transaction();
                transaction.setFromAccount(fromAcc);
                transaction.setToAccount(toAcc);
                transaction.setDescription(description);
                transaction.setAmount(amount);
                transaction.setTransacted(true);
                transaction.setCharge(new BigDecimal("0.005").multiply(amount));
                transactionRepository.save(transaction);

                // 3, Save a record for user:
                fromAcc.getTransactions().add(transaction);

                send = true;
            }
        }

        return send;
    }

}
