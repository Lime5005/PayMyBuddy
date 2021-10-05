package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.AccountRepository;
import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.User;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountServiceTests {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAccount() {
        Account account = new Account();
        account.setCreationDate(new Date().toInstant());
        account.setBalance(new BigDecimal(1000));
        User user = userRepository.getById(6);
        account.setUser(user);
        accountService.save(account);
        assertEquals(2, accountRepository.findAll().size(), "Size");
        assertEquals(account.getUser().getId(), user.getId());
    }

    @Test
    public void testFindByUserId() {
        Account account = accountService.findByUserId(6);
        assertEquals(0, account.getBalance().compareTo(new BigDecimal(1000)));
    }

    @Test
    public void testFindById() {
        Optional<Account> account =  accountService.findById(9);
        assertEquals(6, account.get().getUser().getId());
    }

}
