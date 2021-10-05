package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.User;
import com.lime.paymybuddy.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setCreationDate(new Date().toInstant());
        user.setUserName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("123");
        userService.save(user);
        assertEquals("John", userRepository.findByEmail("john@gmail.com").getUserName());
    }

    @Test
    public void testFindUserById() {
        Optional<User> user = userService.findById(4);
        assertEquals("John", user.get().getUserName());
    }

    @Test
    public void testFindAllUser() {
        List<User> users = userService.findAll();
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    public void testDeleteUserById() {
        userService.deleteById(4);
        assertFalse(userRepository.findById(4).isPresent());
    }

    @Test
    public void testDeleteUserByEmail() {
        userService.deleteByEmail("john@gmail.com");
        User user = userRepository.findByEmail("john@gmail.com");
        assertNull(user);
    }

}
