package com.lime.paymybuddy.service;

import com.lime.paymybuddy.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Integer save(Transaction transaction);
    List<Transaction> findAll();
    Optional<Transaction> findById(int id);
    Page<Transaction> findTransactionsByFromAccount_Id(int id, PageRequest pageRequest);
    List<Transaction> findTransactionsByFromAccount_Id(int id);
}
