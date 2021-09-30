package com.lime.paymybuddy;

import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.User;
import com.lime.paymybuddy.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PayMyBuddyApplicationTests {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setCreationDate(new Date().toInstant());
        user.setUserName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("123");
//        userService.save(user);
        assertEquals("John", userRepository.findUserByEmail("john@gmail.com").getUserName());
    }

    @Test
    public void testFindUserById() {
        Optional<User> user = userService.findById(6);
        assertEquals("James", user.get().getUserName());
    }

    @Test
    public void testFindAllUser() {
        List<User> users = userService.findAll();
        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    public void testDeleteUserById() {
        userService.deleteById(7);
        assertFalse(userRepository.findById(7).isPresent());
    }

    @Test
    public void testDeleteUserByEmail() {
        userService.deleteUserByEmail("john@gmail.com");
        User user = userRepository.findUserByEmail("john@gmail.com");
//        System.out.println(user);
        assertNull(user);
    }

}
