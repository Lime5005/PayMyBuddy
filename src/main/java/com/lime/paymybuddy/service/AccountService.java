package com.lime.paymybuddy.service;

import com.lime.paymybuddy.model.Account;

import java.util.Optional;

public interface AccountService {
    Integer save(Account account);
    Optional<Account> findById(int id);
    Account findByUserId(int id);

}
