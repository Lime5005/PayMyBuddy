package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.DaoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<DaoUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<DaoUser> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public DaoUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Integer save(DaoUser user) {
        //UserDetailsService called to save UserDto as DaoUser, here is DaoUser saved As DaoUser, so it's not duplicate method.
        if (user != null) {
            DaoUser newUser = new DaoUser();
            newUser.setUserName(user.getUserName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(newUser).getId();
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

}
