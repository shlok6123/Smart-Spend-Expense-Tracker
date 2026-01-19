package com.shlok.SmartSpend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BudgetDto {

    @NotBlank(message = "Category Cannot be Blank")
    private String category;

    @NotNull(message = "Limit amount must be there")
    @Positive(message = "Limit Amount Should be Positive")
    private BigDecimal limitAmount;
}
