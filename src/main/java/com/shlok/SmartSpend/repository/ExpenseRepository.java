package com.shlok.SmartSpend.repository;

import com.shlok.SmartSpend.dto.CategorySum;
import com.shlok.SmartSpend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer> {

    @Query("Select SUM(e.amount) from Expense e where e.user.id=:userId")
    public BigDecimal getTotalSpentByUser(@Param("userId")Integer userId);


    // Inside ExpenseRepository interface

    // Copy this method carefully
    @Query("SELECT new com.shlok.SmartSpend.dto.CategorySum(e.category, SUM(e.amount)) " +
            "FROM Expense e WHERE e.user.id = :userId " +
            "GROUP BY e.category")
    List<CategorySum> getCategoryBreakdown(@Param("userId") Integer userId);

    @Query("SELECT new com.shlok.SmartSpend.dto.CategorySum(e.category, SUM(e.amount)) " +
            "FROM Expense e " +
            "WHERE e.user.id = :userId " +
            "AND e.date BETWEEN :startDate AND :endDate " + // <--- The New Filter
            "GROUP BY e.category")
    List<CategorySum> getCategoryAnalyticsByDate(
            @Param("userId") Integer userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}



