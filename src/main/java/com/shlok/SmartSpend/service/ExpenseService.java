package com.shlok.SmartSpend.service;

import com.shlok.SmartSpend.dto.BudgetResponseDto;
import com.shlok.SmartSpend.dto.CategorySum;
import com.shlok.SmartSpend.model.Budget;
import com.shlok.SmartSpend.model.Expense;
import com.shlok.SmartSpend.model.User;
import com.shlok.SmartSpend.repository.BudgetRepository;
import com.shlok.SmartSpend.repository.ExpenseRepository;
import com.shlok.SmartSpend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BudgetRepository budgetRepository;



    public List<CategorySum> getCategoryAnalytics(Integer userId){
        return expenseRepository.getCategoryBreakdown(userId);
    }

    public List<CategorySum> getAnalyticsByDate(Integer userId, LocalDate startDate,LocalDate endDate){
        if(startDate.isAfter(endDate)){
            throw new RuntimeException("Start Date Should be less than End Date");
        }
       return expenseRepository.getCategoryAnalyticsByDate(userId,startDate,endDate);
    }

    public BudgetResponseDto createExpense(Expense expense, Integer userId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User Not Found with id "+userId));
        expense.setUser(user);

        String response="Expense Added Sucessfully!";
        log.info("--- Processing Expense for User: {} | Category: {} ---", userId, expense.getCategory());
        Optional<Budget> budgetOption=budgetRepository.findByUserIdAndCategory(userId,expense.getCategory());
        if(budgetOption.isPresent()){
            Budget budget=budgetOption.get();
            log.info("✅ Budget found! Limit: {}", budget.getLimitAmount());
            BigDecimal totalSpent=expenseRepository.getTotalSpentByCategory(userId,expense.getCategory());

            if(totalSpent==null) totalSpent=BigDecimal.ZERO;

            log.info("💰 Previously Spent: {}", totalSpent);
            log.info("💸 Trying to spend: {}", expense.getAmount());
            BigDecimal newTotal = totalSpent.add(expense.getAmount());
            log.info("🧮 New Total would be: {}", newTotal);

            if(newTotal.compareTo(budget.getLimitAmount())>0){
                log.warn("⚠️ OVER BUDGET! Limit: {} | New Total: {}", budget.getLimitAmount(), newTotal);
               response="⚠️ WARNING: You have exceeded your budget for " + expense.getCategory() + "!";
            }else{
                log.info("Expense Added Still In Budget:");
            }
        }else{
            log.warn("❌ No Budget found for category: '{}'. Watchdog skipped.", expense.getCategory());
        }
        Expense savedExpense=expenseRepository.save(expense);
        return new BudgetResponseDto(savedExpense,response);
    }

    public BigDecimal getTotalSpentByCategory(Integer userId, String category){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User Not Found: "));
         BigDecimal totalspent= expenseRepository.getTotalSpentByCategory(userId,category);
        return (totalspent != null) ? totalspent : BigDecimal.ZERO;
    }

}
