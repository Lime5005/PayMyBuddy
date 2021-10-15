package com.lime.paymybuddy.controller;

import com.lime.paymybuddy.model.DaoUser;
import com.lime.paymybuddy.model.Friends;
import com.lime.paymybuddy.model.dto.FriendDto;
import com.lime.paymybuddy.service.AccountService;
import com.lime.paymybuddy.service.FriendsService;
import com.lime.paymybuddy.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/friend")
public class FriendController {

    private AccountService accountService;
    private UserService userService;
    private FriendsService friendsService;

    public FriendController(AccountService accountService, UserService userService, FriendsService friendsService) {
        this.accountService = accountService;
        this.userService = userService;
        this.friendsService = friendsService;
    }

    @PostMapping
    public String addFriend(Authentication authentication, @ModelAttribute("newFriend") FriendDto friendDto, Model model) {
        //1, Find user first
        String email = authentication.getName();
        DaoUser user = userService.findByEmail(email);
        int errorType = 0;
        boolean success = false;

        //2, Check if friend is in database or has an account
        String friendEmail = friendDto.getFriendEmail();
        DaoUser friendToBe = userService.findByEmail(friendEmail);

        if (friendToBe == null || friendToBe.getAccount() == null) {
            errorType = 4;
        } else {
            //4, Save
            Friends friends = new Friends();
            friends.setUser(user);
            friends.setFriend(friendToBe);
            friendsService.save(friends);
            success = true;
        }

        model.addAttribute("success", success);
        model.addAttribute("errorType", errorType);

        return "result";
    }
}
