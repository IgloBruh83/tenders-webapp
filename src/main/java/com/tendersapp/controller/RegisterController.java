package com.tendersapp.controller;

import com.tendersapp.model.Account;
import com.tendersapp.repository.AccountRepository;
import com.tendersapp.service.PasswordControl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.ZonedDateTime;

@Controller
public class RegisterController {

    private final AccountRepository AR;

    public RegisterController(AccountRepository accountRepository) {
        this.AR = accountRepository;
    }

    @PostMapping("/register")
    public String register(
            @RequestParam("taxId") String taxId,
            @RequestParam("fullName") String fullName,
            @RequestParam(value = "shortName", required = false) String shortName,
            @RequestParam("email") String email,
            @RequestParam(value = "login", required = false) String login,
            @RequestParam("password") String password,
            @RequestParam("region") String region,
            @RequestParam("post_index") String post_index,
            @RequestParam("city") String city,
            @RequestParam("address") String address,
            @RequestParam("tel") String tel,
            @RequestParam(value = "tel2", required = false) String tel2,
            Model model
    ) {
        Account account = new Account();
        account.setTaxId(taxId);
        account.setFullName(fullName);
        account.setShortName(shortName);
        account.setEmail(email);
        account.setLogin(login);
        account.setPassword(PasswordControl.hash(password));
        account.setRegion(region);
        account.setPostIndex(post_index);
        account.setCity(city);
        account.setAddress(address);
        account.setTel(tel);
        account.setTel2(tel2);
        account.setRegisterDate(ZonedDateTime.now());
        account.setSuspended(false);

        AR.save(account);
        return "redirect:/login.html";
    }
}