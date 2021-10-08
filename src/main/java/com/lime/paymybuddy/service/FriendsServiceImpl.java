package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.FriendsRepository;
import com.lime.paymybuddy.model.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FriendsServiceImpl implements FriendsService {

    private final FriendsRepository friendsRepository;

    @Autowired
    public FriendsServiceImpl(FriendsRepository friendsRepository) {
        this.friendsRepository = friendsRepository;
    }

    public void save(Friends friends) {
        friendsRepository.save(friends);
    }

    @Override
    public void delete(Friends friends) {
        friendsRepository.delete(friends);
    }

    @Override
    public Friends findByFriend_Id(int id) {
        return friendsRepository.findByFriend_Id(id);
    }

    @Override
    public List<Friends> findAllByUser_Id(int id) {
        return friendsRepository.findAllByUser_Id(id);
    }

    @Override
    public Integer deleteByFriend_Id(int id) {
        return friendsRepository.deleteByFriend_Id(id);
    }

}
