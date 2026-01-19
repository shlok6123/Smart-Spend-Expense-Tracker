package com.shlok.SmartSpend.controller;

import com.shlok.SmartSpend.dto.BudgetDto;
import com.shlok.SmartSpend.model.Budget;
import com.shlok.SmartSpend.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;
    @PostMapping("/{id}")
    public ResponseEntity<Budget> createBudget(@PathVariable Integer id, @Valid @RequestBody BudgetDto budgetDto){
       Budget budget=budgetService.createOrUpdateBudget(id,budgetDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(budget);
    }
}
