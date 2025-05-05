package com.tendersapp.controller;

import com.tendersapp.dto.LoginDTO;
import com.tendersapp.model.Account;
import com.tendersapp.repository.AccountRepository;
import com.tendersapp.service.PasswordControl;
import com.tendersapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // templates/login.html 
    }

    @PostMapping("/login")
    public String doLogin(
            @RequestParam String login,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        LoginDTO dto = new LoginDTO();
        dto.setLogin(login);
        dto.setPassword(password);

        Account account = userService.loginUser(dto);

        if (account == null) {
            model.addAttribute("error", "Невірний логін або пароль");
            return "login";
        }

        session.setAttribute("user", account);
        return "redirect:/";
    }
}
