package com.shlok.SmartSpend.service;

import com.shlok.SmartSpend.dto.BudgetDto;
import com.shlok.SmartSpend.dto.BudgetResponseDto;
import com.shlok.SmartSpend.dto.BudgetStatusDto;
import com.shlok.SmartSpend.model.Budget;
import com.shlok.SmartSpend.model.User;
import com.shlok.SmartSpend.repository.BudgetRepository;
import com.shlok.SmartSpend.repository.ExpenseRepository;
import com.shlok.SmartSpend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

    public Budget createOrUpdateBudget(Integer userId, BudgetDto budgetDto){
         User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
         Budget budget=budgetRepository.findByUserIdAndCategory(userId,budgetDto.getCategory()).orElse(new Budget());
         budget.setUser(user);
         budget.setLimitAmount(budgetDto.getLimitAmount());
         budget.setCategory(budgetDto.getCategory());

         return budgetRepository.save(budget);
    }

    public List<BudgetStatusDto> listAllBudget(Integer userId) {
        // 1. Fetch RAW Entities (Budget), not DTOs
        List<Budget> budgets = budgetRepository.findAllByUserId(userId);
        // Note: Cast userId to (long) if your ID is Long in DB

        List<BudgetStatusDto> reportList = new ArrayList<>();

        // 2. Loop through the ENTITIES
        for (Budget budget : budgets) {

            // Your Math Logic is Perfect below! 👇
            BigDecimal totalSpent = expenseRepository.getTotalSpentByCategory(userId, budget.getCategory());

            if (totalSpent == null) totalSpent = BigDecimal.ZERO;

            BigDecimal remaining = budget.getLimitAmount().subtract(totalSpent);

            double percentage = 0.0;
            if (budget.getLimitAmount().compareTo(BigDecimal.ZERO) > 0) {
                double spentVal = totalSpent.doubleValue();
                double limitVal = budget.getLimitAmount().doubleValue();

                percentage = (spentVal / limitVal) * 100;
            }

            BudgetStatusDto dto = new BudgetStatusDto(
                    budget.getCategory(),
                    budget.getLimitAmount(),
                    totalSpent,
                    remaining,
                    percentage
            );
            reportList.add(dto);
        }
        return reportList;
    }
}
