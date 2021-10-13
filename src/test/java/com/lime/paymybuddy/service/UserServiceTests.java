package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.DaoUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTests {

    private final DaoUser user = new DaoUser();

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void initUser() {

        user.setUserName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("456");

        user.setId(userService.save(user));
    }

    @AfterEach
    public void cleanUser() {
        userRepository.deleteAll();
    }

    @Test
    public void testFindUserById() {
        Optional<DaoUser> userFound = userService.findById(user.getId());
        assertEquals("John", userFound.get().getUserName());
    }

    @Test
    public void testFindAllUser() {
        List<DaoUser> users = userService.findAll();
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    public void testDeleteUserById() {
        userService.deleteById(user.getId());
        assertFalse(userRepository.findById(user.getId()).isPresent());
    }

    @Test
    public void testDeleteUserByEmail() {
        userService.deleteByEmail("john@gmail.com");
        DaoUser user = userRepository.findByEmail("john@gmail.com");
        assertNull(user);
    }

}
