package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.FriendsRepository;
import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.Friends;
import com.lime.paymybuddy.model.DaoUser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FriendsServiceTests {

    private DaoUser user1 = new DaoUser();
    private DaoUser user2 = new DaoUser();

    private Friends friends = new Friends();

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private FriendsRepository friendsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void testSaveFriends() {
        user1.setUserName("Ayu");
        user1.setEmail("ayu@gmail.com");
        user1.setPassword("123");
        userService.save(user1);
        user2.setUserName("Nil");
        user2.setEmail("nil@gmail.com");
        user2.setPassword("123");
        userService.save(user2);
        friends.setUser(user1);
        friends.setFriend(user2);
        friendsService.save(friends);
    }

    @AfterAll
    public void clean() {
        // User -> Friends, OneToMany, so delete child row: friends first, then user.
        friendsRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void testFindAllByUserId() {
        List<Friends> friends = friendsService.findAllByUser_Id(user1.getId());
        assertEquals(1, friends.size());
    }

    @Test
    @Order(2)
    public void testFindByFriendId() {
        Friends friends = friendsService.findByFriend_Id(user2.getId());
        assertEquals("nil@gmail.com", friends.getFriend().getEmail());
    }

    @Test
    @Order(3)
    public void testDeleteByFriendId() {
        Integer id = friendsService.deleteByFriend_Id(user2.getId());
        assertNull(friendsRepository.findByFriend_Id(id));
    }

}
