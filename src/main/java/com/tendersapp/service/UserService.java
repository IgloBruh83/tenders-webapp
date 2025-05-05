package com.tendersapp.service;

import com.tendersapp.dto.LoginDTO;
import com.tendersapp.dto.RegisterDTO;
import com.tendersapp.model.Account;
import com.tendersapp.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final AccountRepository accountRepository;
    private final PasswordControl passwordControl;

    public UserService(AccountRepository accountRepository, PasswordControl passwordControl) {
        this.accountRepository = accountRepository;
        this.passwordControl = passwordControl;
    }

    public Account loginUser(LoginDTO dto) {
        Optional<Account> acc = accountRepository.findByLogin(dto.getLogin());

        if (acc.isEmpty() || !passwordControl.match(dto.getPassword(), acc.get().getPassword())) {
            return null;
        }

        return acc.get();
    }

    public void registerUser(RegisterDTO dto) {
        Account account = new Account();
        account.setTaxId(dto.getTaxId());
        account.setFullName(dto.getFullName());
        account.setShortName(dto.getShortName());
        account.setEmail(dto.getEmail());
        account.setLogin(dto.getLogin());
        account.setPassword(passwordControl.hash(dto.getPassword()));
        account.setRegion(dto.getRegion());
        account.setPostIndex(dto.getPostIndex());
        account.setCity(dto.getCity());
        account.setAddress(dto.getAddress());
        account.setTel(dto.getTel());
        account.setTel2(dto.getTel2());
        account.setRegisterDate(ZonedDateTime.now());
        account.setSuspended(false);

        accountRepository.save(account);
    }

}
