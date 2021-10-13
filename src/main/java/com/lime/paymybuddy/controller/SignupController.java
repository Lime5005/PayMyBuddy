package com.lime.paymybuddy.controller;

import com.lime.paymybuddy.model.User;
import com.lime.paymybuddy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private UserService userService;

    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupView() {
        return "signup";
    }

    @PostMapping
    private String signupUser(@ModelAttribute User user, Model model, RedirectAttributes redirAttrs) {
        String signupError = null;
        User existsUser = userService.findByEmail(user.getEmail());
        if (existsUser != null) {
            signupError = "The email already exists";
        }
        if (signupError == null) {
            Integer id = userService.save(user);
            if (id == null) {
                signupError = "There was an error. Please try again.";
            }
        }

        if (signupError == null) {
            redirAttrs.addFlashAttribute("message", "You've successfully signed up, please login.");
            return "redirect:/login";
        } else {
            model.addAttribute("signupError", true);
        }

        return "signup";

    }

}
