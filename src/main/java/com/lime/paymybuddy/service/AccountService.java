package com.lime.paymybuddy.service;

import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.Transaction;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {
    Integer save(Account account);
    Optional<Account> findById(int id);
    Account findByUserId(int id);
    Boolean sendMoney(Account fromAcc, Account toAcc, BigDecimal amount);

}
