package com.lime.paymybuddy.service;

import com.lime.paymybuddy.model.Friends;

import java.util.List;

public interface FriendsService {
    void save(Friends friends);
    void delete(Friends friends);
    Friends findByFriend_Id(int id);
    List<Friends> findAllByUser_Id(int id);
    Integer deleteByFriend_Id(int id);
}
