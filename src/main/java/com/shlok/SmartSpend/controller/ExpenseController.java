package com.shlok.SmartSpend.controller;

import com.shlok.SmartSpend.dto.CategorySum;
import com.shlok.SmartSpend.model.Expense;
import com.shlok.SmartSpend.service.ExpenseService;
import com.shlok.SmartSpend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/expenses")
public class ExpenseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/{userId}")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense, @PathVariable Integer userId){
       Expense savedExpense= userService.createExpense(expense,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
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
}
