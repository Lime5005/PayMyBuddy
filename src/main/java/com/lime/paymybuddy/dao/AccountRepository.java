package com.lime.paymybuddy.dao;

import com.lime.paymybuddy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("select a from Account a where a.user.id = ?1")
    Account findByUserId(int id);

    @Transactional
    @Modifying
    @Query("update Account a set a.balance = ?2 where a.id = ?1")
    void update(Integer id, BigDecimal balance);
}
