package com.lime.paymybuddy.dao;

import com.lime.paymybuddy.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("select a from Account a where a.user.id = ?1")
    Account findByUserId(int id);
}
