package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.AccountRepository;
import com.lime.paymybuddy.dao.TransactionRepository;
import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.Transaction;
import com.lime.paymybuddy.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransactionServiceTests {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSavaTransaction() {
        Transaction transaction = new Transaction();

        transaction.setAmount(new BigDecimal(20));

        Account fromAccount = new Account();

        fromAccount.setBalance(new BigDecimal(8000));
        accountRepository.save(fromAccount);

        User user = new User();

        user.setUserName("Foo");
        user.setEmail("foo@gmail.com");
        user.setPassword("foo");
        user.setAccount(fromAccount);
        userRepository.save(user);

        fromAccount.setUser(user);

        Account toAccount = new Account();

        toAccount.setBalance(new BigDecimal(0));
        accountRepository.save(toAccount);
        User toUser = new User();
        toUser.setUserName("Bar");
        toUser.setEmail("bar@gmail.com");
        toUser.setPassword("bar");
        toUser.setAccount(toAccount);
        userRepository.save(toUser);
        toAccount.setUser(toUser);

        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setDescription("Gift for birthday");

        transactionRepository.save(transaction);

        assertEquals(1, transactionRepository.findAll().size());

    }

}
