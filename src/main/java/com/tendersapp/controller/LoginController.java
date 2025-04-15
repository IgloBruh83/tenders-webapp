package com.tendersapp.controller;

import com.tendersapp.model.Account;
import com.tendersapp.repository.AccountRepository;
import com.tendersapp.service.PasswordControl;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
public class LoginController {

    private final AccountRepository AR;

    public LoginController(AccountRepository accountRepository) {
        this.AR = accountRepository;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String login,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        Optional<Account> acc = AR.findByLogin(login);

        if (acc.isEmpty() || !PasswordControl.match(password, acc.get().getPassword())) {
            model.addAttribute("error", "Невірний логін або пароль");
            return "login"; // шаблон login.html
        }

        // якщо все добре — зберігаємо в сесію логін або весь об'єкт
        session.setAttribute("user", acc);
        return "redirect:/dashboard";
    }
}
