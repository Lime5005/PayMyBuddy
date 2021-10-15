package com.lime.paymybuddy.service;

import com.lime.paymybuddy.dao.FriendsRepository;
import com.lime.paymybuddy.dao.UserRepository;
import com.lime.paymybuddy.model.DaoUser;
import com.lime.paymybuddy.model.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class FriendsServiceImpl implements FriendsService {

    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendsServiceImpl(FriendsRepository friendsRepository, UserRepository userRepository) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Friends friends) {
        friendsRepository.save(friends);
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

    @Override
    public Boolean isFriend(int id1, int id2) {
        boolean check = false;
        Optional<DaoUser> user1 = userRepository.findById(id1);
        Optional<DaoUser> user2 = userRepository.findById(id2);
        List<Friends> friends = friendsRepository.findAll();
        if (user1.isPresent() && user2.isPresent()) {
            for (Friends friend : friends) {
                Integer idFriend = friend.getFriend().getId();
                Integer idUser = friend.getUser().getId();
                if ((idFriend == id1 && idUser == id2) || idFriend == id2 && idUser == id1) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    @Override
    public Set<DaoUser> findAllMyFriends(int id) {
        HashSet<DaoUser> friendsSet = new HashSet<>();
        Optional<DaoUser> user = userRepository.findById(id);
        List<Friends> friends = friendsRepository.findAll();
        if (user.isPresent()) {
            for (Friends friend : friends) {
                // Others add me as friend:
                if (friend.getFriend().getId() == id) {
                    friendsSet.add(friend.getUser());
                    // I add other as friend:
                } else if (friend.getUser().getId() == id) {
                    friendsSet.add(friend.getFriend());
                }
            }
        }
        return friendsSet;
    }

}
