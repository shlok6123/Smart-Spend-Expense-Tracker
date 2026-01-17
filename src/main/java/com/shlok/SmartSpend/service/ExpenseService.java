package com.shlok.SmartSpend.service;

import com.shlok.SmartSpend.dto.CategorySum;
import com.shlok.SmartSpend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    public List<CategorySum> getCategoryAnalytics(Integer userId){
        return expenseRepository.getCategoryBreakdown(userId);
    }
}
