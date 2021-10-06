package com.lime.paymybuddy.dao;

import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findTransactionsByFromAccount_Id(int id);
}
