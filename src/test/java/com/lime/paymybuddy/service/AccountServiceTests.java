package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.AccountRepository;
import com.lime.paymybuddy.dao.TransactionRepository;
import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.DaoUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTests {

    private Account fromAccount = new Account();
    private Account toAccount = new Account();

    private DaoUser fromUser = new DaoUser();
    private DaoUser toUser = new DaoUser();

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeAll
    public void initAccountsWithUsers() {
        fromAccount.setBalance(new BigDecimal(1000));
        fromUser.setUserName("Foo");
        fromUser.setEmail("foo@gmail.com");
        fromUser.setPassword("foo");
        fromUser.setId(userService.save(fromUser));
        fromAccount.setUser(fromUser);
        accountService.save(fromAccount);

        toAccount.setBalance(new BigDecimal(0));
        toUser.setUserName("Bar");
        toUser.setEmail("bar@gmail.com");
        toUser.setPassword("bar");
        toUser.setId(userService.save(toUser));
        toAccount.setUser(toUser);
        accountService.save(toAccount);

    }

    @AfterAll
    public void cleanAccount() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }

    //If @AfterEach, the findByUserId doesn't work; if no order, the sendMoney will not work.
    @Test
    @Order(1)
    public void testSendMoney() {
//Test passed when I initiate `transactions = new HashSet<>();` in Account.class;
        accountService.sendMoney(fromAccount, toAccount, new BigDecimal(200));
        assertEquals(0, toAccount.getBalance().compareTo(new BigDecimal(200)));
    }

    @Test
    @Order(2)
    public void testFindByUserId() {
        Account account = accountService.findByUserId(fromUser.getId());
        assertEquals(fromUser.getId(), account.getUser().getId());
    }

}
