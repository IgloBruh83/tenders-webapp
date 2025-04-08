package com.tendersapp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordControl {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hash (String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public static boolean match (String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}
