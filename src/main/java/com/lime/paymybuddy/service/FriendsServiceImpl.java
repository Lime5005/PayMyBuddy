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
    public Integer isFriend(int id1, int id2) {
        Integer friend = 0;
        Optional<DaoUser> user1 = userRepository.findById(id1);
        Optional<DaoUser> user2 = userRepository.findById(id2);
        if (user1.isPresent() && user2.isPresent()) {
            friend = friendsRepository.isFriend(id1, id2);

        }
        return friend;
    }

    /**
     * Method for a User to find all his friends by DaoUser id.
     * @param id DaoUser id.
     * @return A Set of DaoUser.
     */
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
