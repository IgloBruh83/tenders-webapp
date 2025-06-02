package com.tendersapp.dto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProposalDTO {

    private Integer id;

    @ManyToOne
    private String creatorId;

    @NotNull(message = "ID тендера обов'язковий")
    private Integer tenderId;

    @Min(value = 1, message = "Тривалість плану повинна бути більше 0")
    private int planLength;

    @DecimalMin(value = "0.0", inclusive = false, message = "Бюджет повинен бути більше 0")
    private float budget;

    @NotBlank(message = "Опис не може бути порожнім")
    @Size(max = 2000, message = "Опис не повинен перевищувати 2000 символів")
    private String description;
}
