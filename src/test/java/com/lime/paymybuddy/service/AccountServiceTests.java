package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.AccountRepository;
import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.Transaction;
import com.lime.paymybuddy.model.User;
import lombok.val;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountServiceTests {

    private final Account fromAccount = new Account();

    private final User fromUser = new User();

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void initAccountsWithUsers() {
        fromAccount.setBalance(new BigDecimal(1000));
        fromUser.setUserName("Foo");
        fromUser.setEmail("foo@gmail.com");
        fromUser.setPassword("foo");
        fromUser.setId(userService.save(fromUser));
        fromAccount.setUser(fromUser);
        accountService.save(fromAccount);

    }

    @AfterEach
    public void cleanAccount() {
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testFindByUserId() {
        Account account = accountService.findByUserId(fromUser.getId());
        assertEquals(fromUser.getId(), account.getUser().getId());
    }

    @Test
    public void testSendMoney() {
        Account toAccount = new Account();
        User toUser = new User();
        toAccount.setBalance(new BigDecimal(0));
        toUser.setUserName("Bar");
        toUser.setEmail("bar@gmail.com");
        toUser.setPassword("bar");
        toUser.setId(userService.save(toUser));
        toAccount.setUser(toUser);
        accountService.save(toAccount);
//Test passed when I initiate `transactions = new HashSet<>();` in Account.class;
        accountService.sendMoney(fromAccount, toAccount, new BigDecimal(200));
        assertEquals(0, toAccount.getBalance().compareTo(new BigDecimal(200)));
    }

}
