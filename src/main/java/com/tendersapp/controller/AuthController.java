package com.tendersapp.controller;

import com.tendersapp.dto.LoginDTO;
import com.tendersapp.dto.RegisterDTO;
import com.tendersapp.model.Account;
import com.tendersapp.model.Region;
import com.tendersapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
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

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("regions", Region.values());
        model.addAttribute("registrationDto", new RegisterDTO());
        return "register"; // templates/register.html
    }

    @PostMapping("/register")
    public String doRegister(
            @ModelAttribute("registrationDto") @Valid RegisterDTO dto,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(error -> {
                System.out.println("Field: " + error.getField());
                System.out.println("Message: " + error.getDefaultMessage());
            });

            model.addAttribute("regions", Region.values());
            return "register";
        }

        userService.registerUser(dto);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "index";
    }
}