package com.shlok.SmartSpend.repository;

import com.shlok.SmartSpend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer> {

    @Query("Select SUM(e.amount) from Expense e where e.user.id=:userId")
    public BigDecimal getTotalSpentByUser(@Param("userId")Integer userId);
}
