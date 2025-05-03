package com.tendersapp.controller;

import com.tendersapp.dto.RegisterDTO;
import com.tendersapp.model.Region;
import com.tendersapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("regions", Region.values());
        model.addAttribute("registrationDto", new RegisterDTO());
        return "register"; // templates/register.html
    }

    @PostMapping("/register")
    public String doRegister(
            @ModelAttribute("registrationDto") @Valid RegisterDTO registrationDto,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("regions", Region.values());
            return "register";
        }

        userService.registerUser(registrationDto);
        return "redirect:/login";
    }
}