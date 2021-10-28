package com.lime.paymybuddy.service;

import com.lime.paymybuddy.model.DaoUser;
import com.lime.paymybuddy.model.Friends;

import java.util.List;
import java.util.Set;

public interface FriendsService {
    void save(Friends friends);
    Friends findByFriend_Id(int id);
    List<Friends> findAllByUser_Id(int id);
    Integer deleteByFriend_Id(int id);
    Integer isFriend(int id1, int id2);
    Set<DaoUser> findAllMyFriends(int id);
}
