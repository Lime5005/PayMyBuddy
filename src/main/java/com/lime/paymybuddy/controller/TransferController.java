package com.lime.paymybuddy.controller;

import com.lime.paymybuddy.model.Account;
import com.lime.paymybuddy.model.DaoUser;
import com.lime.paymybuddy.model.Transaction;
import com.lime.paymybuddy.service.AccountService;
import com.lime.paymybuddy.service.TransactionService;
import com.lime.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping
    public String getTransfer(Authentication authentication,
                              Model model, @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {

        DaoUser user = userService.findByEmail(authentication.getName());

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Sort sort = Sort.by(
                Sort.Order.desc("createdAt"));

        Account account = accountService.findByUserId(user.getId());
        if (account != null) {
            Page<Transaction> transactionList = transactionService.findTransactionsByFromAccount_Id(account.getId(), PageRequest.of(currentPage - 1, pageSize, sort));
            int totalPages = transactionList.getTotalPages();

            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
        }
        model.addAttribute("transactionList", (account == null) ? new ArrayList<>() : transactionService.findTransactionsByFromAccount_Id(account.getId(), PageRequest.of(currentPage - 1, pageSize, sort)));

        return "transfer";
    }
}
