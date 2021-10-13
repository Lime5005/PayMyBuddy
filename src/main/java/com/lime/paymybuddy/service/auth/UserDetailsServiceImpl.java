package com.lime.paymybuddy.service.auth;

import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.DaoUser;
import com.lime.paymybuddy.model.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DaoUser user = userRepository.findByEmail(email);
//        System.out.println("user = " + user);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with Email: " + email);
        }
        return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public DaoUser save(UserDto userDto) {
        DaoUser user = new DaoUser();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }
}
