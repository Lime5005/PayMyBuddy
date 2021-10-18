package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.TransactionRepository;
import com.lime.paymybuddy.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Integer save(Transaction transaction) {
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(int id) {
        return transactionRepository.findById(id);
    }


    @Override
    public Page<Transaction> findTransactionsByFromAccount_Id(int id, PageRequest pageRequest) {
        return transactionRepository.findTransactionsByFromAccount_Id(id, pageRequest);
    }

    @Override
    public List<Transaction> findTransactionsByFromAccount_Id(int id) {
        return transactionRepository.findTransactionsByFromAccount_Id(id);
    }
}
