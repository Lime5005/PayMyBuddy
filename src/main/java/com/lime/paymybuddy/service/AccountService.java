package com.lime.paymybuddy.service;

import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.dto.AccountDto;

import java.math.BigDecimal;

public interface AccountService {
    Boolean saveOrUpdate(AccountDto accountDto, String email);
    Account findByUserId(int id);
    Boolean sendMoney(Account fromAcc, Account toAcc, BigDecimal amount, String description);

}
