package com.tendersapp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProposalDTO {

    @NotNull(message = "ID не може бути порожнім")
    private Integer id;

    @NotNull(message = "ID користувача обов'язковий")
    private Integer creatorId;

    @Min(value = 1, message = "Тривалість плану повинна бути більше 0")
    private int planLength;

    @DecimalMin(value = "0.0", inclusive = false, message = "Бюджет повинен бути більше 0")
    private float budget;

    @NotBlank(message = "Опис не може бути порожнім")
    @Size(max = 2000, message = "Опис не повинен перевищувати 2000 символів")
    private String description;
}
