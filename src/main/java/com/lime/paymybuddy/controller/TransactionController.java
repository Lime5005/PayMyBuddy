package com.lime.paymybuddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transfer")
public class TransactionController {

    @GetMapping
    public String transferView() {
        return "transfer";
    }

}
