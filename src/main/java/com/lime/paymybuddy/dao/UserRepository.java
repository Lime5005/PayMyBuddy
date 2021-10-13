package com.lime.paymybuddy.dao;


import com.lime.paymybuddy.model.DaoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DaoUser, Integer> {
    DaoUser findByEmail(String email);

    void deleteByEmail(String email);
}
