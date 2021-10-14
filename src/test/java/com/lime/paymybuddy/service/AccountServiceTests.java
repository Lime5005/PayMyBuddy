package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.AccountRepository;
import com.lime.paymybuddy.dao.TransactionRepository;
import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.DaoUser;
import com.lime.paymybuddy.model.dto.AccountDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTests {

    private AccountDto fromAccount = new AccountDto();
    private Account account1 = new Account();
    private AccountDto toAccount = new AccountDto();
    private Account account2 = new Account();


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

    //todo: change the logic
//    @BeforeAll
//    public void initAccountsWithUsers() {
//        fromAccount.setBalance(new BigDecimal(1000));
//        fromUser.setUserName("Foo");
//        fromUser.setEmail("foo@gmail.com");
//        fromUser.setPassword("foo");
//        fromUser.setId(userService.save(fromUser));
//
//        accountService.saveOrUpdate(fromAccount, "foo@gmail.com");
//
//        toAccount.setBalance(new BigDecimal(0));
//        toUser.setUserName("Bar");
//        toUser.setEmail("bar@gmail.com");
//        toUser.setPassword("bar");
//        toUser.setId(userService.save(toUser));
//
//        accountService.saveOrUpdate(toAccount, "bar@gmail.com");
//
//    }

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
        account1.setUser(fromUser);
        account1.setBalance(fromAccount.getBalance());
        
        account2.setUser(toUser);
        account2.setBalance(toAccount.getBalance());
//Test passed when I initiate `transactions = new HashSet<>();` in Account.class;
//        accountService.sendMoney(account1, account2, new BigDecimal(200));
        assertEquals(0, toAccount.getBalance().compareTo(new BigDecimal(200)));
    }

    @Test
    @Order(2)
    public void testFindByUserId() {
        Account account = accountService.findByUserId(fromUser.getId());
        assertEquals(fromUser.getId(), account.getUser().getId());
    }

}
