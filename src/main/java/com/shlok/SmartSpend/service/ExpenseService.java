package com.shlok.SmartSpend.service;

import com.shlok.SmartSpend.dto.CategorySum;
import com.shlok.SmartSpend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    public List<CategorySum> getCategoryAnalytics(Integer userId){
        return expenseRepository.getCategoryBreakdown(userId);
    }

    public List<CategorySum> getAnalyticsByDate(Integer userId, LocalDate startDate,LocalDate endDate){
        if(startDate.isAfter(endDate)){
            throw new RuntimeException("Start Date Should be less than End Date");
        }
       return expenseRepository.getCategoryAnalyticsByDate(userId,startDate,endDate);
    }
}
