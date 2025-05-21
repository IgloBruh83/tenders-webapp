package com.tendersapp.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class TenderDTO {

    @NotNull(message = "ID тендера обов'язковий")
    private Integer id;

    @NotBlank(message = "Назва тендера обов'язкова")
    @Size(max = 255, message = "Назва не повинна перевищувати 255 символів")
    private String title;

    @Size(max = 2000, message = "Опис не повинен перевищувати 2000 символів")
    private String description;

    @NotBlank(message = "Тип тендера обов'язковий")
    private String type;

    @NotBlank(message = "Статус тендера обов'язковий")
    private String status;

    @DecimalMin(value = "0.0", inclusive = false, message = "Бюджет повинен бути більше 0")
    private float budget;

    @NotNull(message = "Дедлайн обов'язковий")
    private ZonedDateTime deadline;

    @NotBlank(message = "Регіон обов'язковий")
    private String region;

    @NotBlank(message = "Місто обов'язкове")
    private String city;

    @NotBlank(message = "Адреса обов'язкова")
    private String address;

    @NotNull(message = "ID творця обов'язковий")
    private Integer creatorId;

    private Integer winnerId;

}