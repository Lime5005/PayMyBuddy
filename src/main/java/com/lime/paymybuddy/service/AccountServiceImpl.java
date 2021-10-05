package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.AccountRepository;
import com.lime.paymybuddy.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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

}
