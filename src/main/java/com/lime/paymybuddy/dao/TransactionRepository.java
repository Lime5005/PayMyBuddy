package com.lime.paymybuddy.dao;

import com.lime.paymybuddy.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
//    List<Transaction> findTransactionsByFromAccount_Id(int id);
    Page<Transaction> findTransactionsByFromAccount_Id(int id, Pageable pageable);
}
