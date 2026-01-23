package com.shlok.SmartSpend.controller;

import com.shlok.SmartSpend.dto.BudgetResponseDto;
import com.shlok.SmartSpend.dto.CategorySum;
import com.shlok.SmartSpend.model.Budget;
import com.shlok.SmartSpend.model.Expense;
import com.shlok.SmartSpend.repository.BudgetRepository;
import com.shlok.SmartSpend.service.ExpenseService;
import com.shlok.SmartSpend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/expenses")
public class ExpenseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private BudgetRepository budgetRepository;

    @PostMapping("/{userId}")
    public ResponseEntity<BudgetResponseDto> createExpense(@Valid  @RequestBody Expense expense, @PathVariable Integer userId){
       BudgetResponseDto savedExpense=expenseService.createExpense(expense,userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
    }

    @GetMapping("/{userId}/{category}")
    public ResponseEntity<BigDecimal> getTotalSpentByCategory(@PathVariable Integer userId,@PathVariable String category){
        BigDecimal totalSpent=expenseService.getTotalSpentByCategory(userId,category);
        return ResponseEntity.ok(totalSpent);

    }

    @GetMapping("/{userId}/total")
    public ResponseEntity<BigDecimal> getTotalSpentByUser(@PathVariable Integer userId){
        BigDecimal totalSpent=userService.getTotalSpentByUser(userId);
        return ResponseEntity.ok(totalSpent);
    }

    @GetMapping("/{userId}/analytics")
    public ResponseEntity<List<CategorySum>> getAnalytics(@PathVariable Integer userId){
        return ResponseEntity.ok(expenseService.getCategoryAnalytics(userId));
    }

    @GetMapping("/{userId}/analytics/date")
    public ResponseEntity<List<CategorySum>> getAnalyticsByDate(@PathVariable Integer userId,
      @RequestParam(required = false) LocalDate startDate,@RequestParam(required = false) LocalDate endDate){
        LocalDate start = (startDate != null) ? startDate : LocalDate.now().withDayOfMonth(1); // Default: 1st of this month
    LocalDate end = (endDate != null) ? endDate : LocalDate.now(); // Default: Today){
        return ResponseEntity.ok(expenseService.getAnalyticsByDate(userId,start,end));
    }
}
