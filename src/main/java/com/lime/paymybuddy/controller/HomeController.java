package com.lime.paymybuddy.controller;

import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.DaoUser;
import com.lime.paymybuddy.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequestMapping("/home")
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

    @GetMapping
    public String getHome(Authentication authentication, Model model) {

        //The name has been override as email:
        DaoUser user = userService.findByEmail(authentication.getName());
        String name = user.getUserName();

//        Account account = accountService.findByUserId(user.getId());//todo:"account" is null
//        model.addAttribute("transactionList", transactionService.findTransactionsByFromAccount_Id(account.getId()));
//        model.addAttribute("friendList", friendsService.findAllByUser_Id(user.getId()));
//
        model.addAttribute("message", "Hi " + name);
        model.addAttribute("balance", user.getAccount().getBalance() == null ? new BigDecimal(0) : user.getAccount().getBalance());
        return "home";
    }
}
