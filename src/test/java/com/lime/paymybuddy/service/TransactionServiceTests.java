package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.AccountRepository;
import com.lime.paymybuddy.dao.TransactionRepository;
import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TransactionServiceTests {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testSavaTransaction() {
        // Only for record, not real transfer.
        Transaction transaction = new Transaction();

        transaction.setAmount(new BigDecimal(200));
        transaction.setFromAccount(accountRepository.findByUserId(12));
        transaction.setToAccount(accountRepository.findByUserId(14));
        transaction.setDescription("Test transfer 200 $.");
        transactionRepository.save(transaction);

        assertTrue(transactionRepository.findById(transaction.getId()).isPresent());

    }

    @Test
    public void testFindById() {
        Optional<Transaction> transaction = transactionService.findById(10);
        String userName = transaction.get().getToAccount().getUser().getUserName();
        assertEquals("Lily", userName);
    }

    @Test
    public void testFindAll() {
        List<Transaction> transactions = transactionService.findAll();
        assertEquals(3, transactions.size());
    }

    @Test
    public void testFindAllByFromAccountId() {
        List<Transaction> transactions = transactionService.findTransactionsByFromAccount_Id(16);
        assertEquals(3, transactions.size());
    }

}
