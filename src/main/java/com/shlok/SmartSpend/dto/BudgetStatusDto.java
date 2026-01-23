package com.shlok.SmartSpend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BudgetStatusDto {

    private String category;
    private BigDecimal limitAmount;
    private BigDecimal totalSpent;
    private BigDecimal remainingAmount;

    private double percentageUsed;

}
