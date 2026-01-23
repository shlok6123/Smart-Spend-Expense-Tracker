package com.shlok.SmartSpend.dto;

import com.shlok.SmartSpend.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BudgetResponseDto {
    private Expense expense;
    private String message;
}
