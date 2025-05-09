package com.tendersapp.controller;

import com.tendersapp.model.Account;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class AccountPageController {

    @GetMapping("/account")
    public String showAccountPage(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("user");
        if (account == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", account);
        return "account";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "/";
    }

}
