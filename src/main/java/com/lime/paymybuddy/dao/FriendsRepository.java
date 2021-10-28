package com.lime.paymybuddy.dao;

import com.lime.paymybuddy.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer> {
    Friends findByFriend_Id(int id);
    List<Friends> findAllByUser_Id(int id);
    Integer deleteByFriend_Id(int id);

    @Query("select count(distinct f.id) from Friends f where (f.friend.id = ?1 and f.user.id = ?2) OR (f.user.id = ?1 and f.friend.id = ?2)")
    Integer isFriend(int id1, int id2);
}
