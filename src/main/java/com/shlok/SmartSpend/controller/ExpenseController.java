package com.shlok.SmartSpend.controller;

import com.shlok.SmartSpend.model.Expense;
import com.shlok.SmartSpend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/expenses")
public class ExpenseController {

    @Autowired
    private UserService userService;

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
}
