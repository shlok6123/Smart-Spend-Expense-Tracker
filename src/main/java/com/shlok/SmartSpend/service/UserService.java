package com.shlok.SmartSpend.service;

import com.shlok.SmartSpend.model.Expense;
import com.shlok.SmartSpend.model.User;
import com.shlok.SmartSpend.repository.ExpenseRepository;
import com.shlok.SmartSpend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

   public Expense createExpense(Expense expense,Integer userId){

       User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User Not Found with id "+userId));

       expense.setUser(user);

       return expenseRepository.save(expense);

   }

   public BigDecimal getTotalSpentByUser(Integer userId){
       BigDecimal total=expenseRepository.getTotalSpentByUser(userId);

       return (total!=null)?total:BigDecimal.ZERO;
   }
}
