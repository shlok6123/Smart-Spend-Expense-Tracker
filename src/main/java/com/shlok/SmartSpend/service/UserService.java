package com.shlok.SmartSpend.service;

import com.shlok.SmartSpend.dto.CategorySum;
import com.shlok.SmartSpend.dto.UserDto;
import com.shlok.SmartSpend.model.Expense;
import com.shlok.SmartSpend.model.User;
import com.shlok.SmartSpend.repository.ExpenseRepository;
import com.shlok.SmartSpend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;


   public User createUser(UserDto userDto){
           User newUser=new User();
           newUser.setEmail(userDto.getEmail());
           newUser.setUsername(userDto.getName());

           return userRepository.save(newUser);
   }


   public BigDecimal getTotalSpentByUser(Integer userId){
       BigDecimal total=expenseRepository.getTotalSpentByUser(userId);
       return (total!=null)?total:BigDecimal.ZERO;
   }


}
