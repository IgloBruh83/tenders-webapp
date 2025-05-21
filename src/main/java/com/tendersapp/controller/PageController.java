package com.tendersapp.controller;

import com.tendersapp.model.Account;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class PageController {

    @GetMapping("/account")
    public String showAccountPage(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("user");
        if (account == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", account);
        return "account";
    }

    @GetMapping("/")
    public String index() {
        return "index"; // = templates/index.html
    }

    @GetMapping("/about")
    public String aboutMe() {
        return "about"; // = templates/about.html
    }

    @GetMapping("/legal")
    public String legal() {
        return "legal"; // = templates/legal.html
    }

}
