package com.tendersapp.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotNull
    @Pattern(regexp = "^(\\d{8}|\\d{10})$", message = "Поштовий індекс має містити рівно 5 цифр")
    private String taxId;

    @NotNull
    @Size(max = 255, message = "Назва має бути не більше 255 символів")
    private String fullName;
    
    @Size(max = 128, message = "Скорочена назва має бути не більше 128 символів")
    private String shortName;

    @NotNull
    @Email(message = "Некоректний формат email")
    @Size(max = 255, message = "Email має бути не більше 255 символів")
    private String email;

    @Size(max = 64, message = "Логін має бути не більше 64 символів")
    private String login;

    @NotNull
    @Size(min = 8, message = "Пароль має бути не менше 8 символів")
    private String password;

    @NotNull
    @Size(max = 18, message = "Телефон має бути не більше 18 символів")
    private String tel;

    @Size(max = 18, message = "Другий телефон має бути не більше 18 символів")
    private String tel2;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Регіон не може бути порожнім")
    private String region;

    @NotNull
    @Pattern(regexp = "\\d{5}", message = "Поштовий індекс має містити рівно 5 цифр")
    private String postIndex;

    @NotNull(message = "Місто не може бути порожнім")
    private String city;

    @NotNull
    @Size(max = 255, message = "Адреса має бути не більше 255 символів")
    private String address;
}

