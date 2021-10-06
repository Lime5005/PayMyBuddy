package com.lime.paymybuddy.service;

import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Integer save(Transaction transaction);
    List<Transaction> findAll();
    Optional<Transaction> findById(int id);
    List<Transaction> findTransactionsByFromAccount_Id(int id);
}
