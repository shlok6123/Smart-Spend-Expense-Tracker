package com.shlok.SmartSpend.repository;

import com.shlok.SmartSpend.dto.BudgetStatusDto;
import com.shlok.SmartSpend.model.Budget;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    Optional<Budget> findByUserIdAndCategory(Integer userId,String category);

    List<Budget>findAllByUserId(Integer userId);
}
