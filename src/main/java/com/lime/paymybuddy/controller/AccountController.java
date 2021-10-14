package com.lime.paymybuddy.controller;

import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.DaoUser;
import com.lime.paymybuddy.model.dto.AccountDto;
import com.lime.paymybuddy.service.AccountService;
import com.lime.paymybuddy.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping
    public String saveOrUpdateAccount(Authentication authentication,
                             @ModelAttribute("newAccount") AccountDto accountDto,
                             Model model) {
        boolean success = false;
        int errorType = 1;
        String email = authentication.getName(); // I override name as email, so here I got email.

        DaoUser user = userService.findByEmail(email);
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
}
