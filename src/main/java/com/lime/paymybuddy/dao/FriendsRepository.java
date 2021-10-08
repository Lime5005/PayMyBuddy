package com.lime.paymybuddy.dao;

import com.lime.paymybuddy.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer> {
    Friends findByFriend_Id(int id);
    List<Friends> findAllByUser_Id(int id);
    Integer deleteByFriend_Id(int id);

}
