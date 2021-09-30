package com.lime.paymybuddy.service;

import com.lime.paymybuddy.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(int id);
    User findUserByEmail(String email);
    void save(User user);
    void deleteById(int id);
    void deleteUserByEmail(String email);
}
