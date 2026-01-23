package com.shlok.SmartSpend.service;

import com.shlok.SmartSpend.dto.BudgetDto;
import com.shlok.SmartSpend.model.Budget;
import com.shlok.SmartSpend.model.User;
import com.shlok.SmartSpend.repository.BudgetRepository;
import com.shlok.SmartSpend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;
    public Budget createOrUpdateBudget(Integer userId, BudgetDto budgetDto){
         User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));
         Budget budget=budgetRepository.findByUserIdAndCategory(userId,budgetDto.getCategory()).orElse(new Budget());
         budget.setUser(user);
         budget.setLimitAmount(budgetDto.getLimitAmount());
         budget.setCategory(budgetDto.getCategory());

         return budgetRepository.save(budget);
    }
}
