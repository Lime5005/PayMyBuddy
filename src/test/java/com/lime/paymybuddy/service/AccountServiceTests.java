package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.AccountRepository;
import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.Transaction;
import com.lime.paymybuddy.model.User;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
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

        account.setBalance(new BigDecimal(6000));
        User user = userRepository.getById(14);
        account.setUser(user);
        accountService.save(account);
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

    @Test
    public void testSendMoney() {
        //Real transfer and save a record.
        //1, Independent of db data:
//        Account fromAccount = new Account();

//        fromAccount.setBalance(new BigDecimal(8000));
//        fromAccount.setUser(userRepository.getById(1));
//        accountService.save(fromAccount);
//
//        Account toAccount = new Account();
//        toAccount.setBalance(new BigDecimal(0));
//        toAccount.setUser(userRepository.getById(2));
//        accountService.save(toAccount);

        //2, Depend on data from db:
        Account fromAccount = accountService.findByUserId(14);
        Account toAccount = accountService.findByUserId(12);

        accountService.sendMoney(fromAccount, toAccount, new BigDecimal(300));

        assertEquals(0, toAccount.getBalance().compareTo(new BigDecimal(4300)));

    }

}