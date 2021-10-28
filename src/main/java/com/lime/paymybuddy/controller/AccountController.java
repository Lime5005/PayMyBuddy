package com.lime.paymybuddy.controller;

import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.DaoUser;
import com.lime.paymybuddy.model.dto.AccountDto;
import com.lime.paymybuddy.model.dto.TransactionDto;
import com.lime.paymybuddy.service.AccountService;
import com.lime.paymybuddy.service.FriendsService;
import com.lime.paymybuddy.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private UserService userService;
    private FriendsService friendsService;

    public AccountController(AccountService accountService, UserService userService, FriendsService friendsService) {
        this.accountService = accountService;
        this.userService = userService;
        this.friendsService = friendsService;
    }

    @PostMapping("/edit")
    public String saveOrUpdateAccount(Authentication authentication,
                             @ModelAttribute("newAccount") AccountDto accountDto,
                             Model model) {
        boolean success = false;
        int errorType = 1;
        String email = authentication.getName(); // I override name as email, so here I got email.

        if (accountDto.getBalance().compareTo(new BigDecimal(0)) < 0 ) {
            return "error-400";
        }

        boolean saved = accountService.saveOrUpdate(accountDto, email);
        if (saved) {
            success = true;
        }

        model.addAttribute("success", success);
        model.addAttribute("errorType", errorType);

        return "result";
    }

    @PostMapping("/transfer")
    public String sendMoney(Authentication authentication,
                            @ModelAttribute("newTransfer") TransactionDto transactionDto, Model model) {
        boolean isFriend = false;
        boolean success = false;
        int errorType = 0;
        boolean sent;

        //1, Get user:
        String email = authentication.getName();
        DaoUser fromUser = userService.findByEmail(email);

        //2, Get friend:
        String toEmail = transactionDto.getToEmail();
        DaoUser toUser = userService.findByEmail(toEmail);

        //3, Check if both accounts exist:
        Account fromAcc = accountService.findByUserId(fromUser.getId());
        Account toAcc = accountService.findByUserId(toUser.getId());

        if (toAcc == null) {
            errorType = 4;
        } else if (fromAcc == null) {
            errorType = 1;
        } else {
            //4, Check if friend is in connections:
            if (friendsService.isFriend(fromUser.getId(), toUser.getId()) == 1) {
                isFriend = true;
            }

            //5, sendMoney:
            if (isFriend) {
                sent = accountService.sendMoney(fromAcc, toAcc, transactionDto.getAmount(), transactionDto.getDescription());
                if (sent) {
                    success = true;
                } else {
                    errorType = 3;
                }
            }

        }

        model.addAttribute("success", success);
        model.addAttribute("errorType", errorType);

        return "result";

    }
}
