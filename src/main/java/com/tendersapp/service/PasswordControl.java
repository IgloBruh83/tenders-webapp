package com.tendersapp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordControl {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String hash (String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public boolean match (String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}
