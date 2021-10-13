package com.lime.paymybuddy.service;

import com.lime.paymybuddy.model.DaoUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<DaoUser> findAll();
    Optional<DaoUser> findById(int id);
    DaoUser findByEmail(String email);
    Integer save(DaoUser user);
    void deleteById(int id);
    void deleteByEmail(String email);
}
