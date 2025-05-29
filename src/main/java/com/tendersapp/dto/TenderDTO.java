package com.tendersapp.dto;

import com.tendersapp.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class TenderDTO {

    private Integer id;

    @NotBlank(message = "Назва тендера обов'язкова")
    @Size(max = 255, message = "Назва не повинна перевищувати 255 символів")
    private String title;

    @Size(max = 2000, message = "Опис не повинен перевищувати 2000 символів")
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @DecimalMin(value = "0.0", inclusive = false, message = "Бюджет повинен бути більше 0")
    private float budget;

    private ZonedDateTime deadline;

    @NotBlank(message = "Регіон обов'язковий")
    private String region;

    @NotBlank(message = "Місто обов'язкове")
    private String city;

    @NotBlank(message = "Адреса обов'язкова")
    private String address;

    @ManyToOne
    private String creatorId;

    private String creatorName;

    private String creatorTel;

    private String creatorTel2;

    private String winnerId;

}