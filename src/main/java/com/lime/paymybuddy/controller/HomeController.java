package com.lime.paymybuddy.controller;

import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.DaoUser;
import com.lime.paymybuddy.service.AccountService;
import com.lime.paymybuddy.service.FriendsService;
import com.lime.paymybuddy.service.TransactionService;
import com.lime.paymybuddy.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping
public class HomeController {
    private UserService userService;
    private AccountService accountService;
    private TransactionService transactionService;
    private FriendsService friendsService;

    public HomeController(UserService userService, AccountService accountService, TransactionService transactionService, FriendsService friendsService) {
        this.userService = userService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.friendsService = friendsService;
    }

    @GetMapping({"/", "/home"})
    public String getHome(Authentication authentication,
                          Model model) {

        //The name has been override as email:
        DaoUser user = userService.findByEmail(authentication.getName());
        String name = user.getUserName();
        Set<DaoUser> myFriends = friendsService.findAllMyFriends(user.getId());

        Account account = accountService.findByUserId(user.getId());

        model.addAttribute("transactionList", (account == null) ? new ArrayList<>() : transactionService.findTransactionsByFromAccount_Id(account.getId()));
        model.addAttribute("friendList", myFriends == null ? new HashSet<>() : myFriends);

        model.addAttribute("message", "Hi " + name);
        model.addAttribute("balance", user.getAccount() == null ? new BigDecimal(0) : user.getAccount().getBalance());
        return "home";
    }
}
