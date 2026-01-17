package com.shlok.SmartSpend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CategorySum {

    private String category;
    private BigDecimal totalAmount;
    
    
    public CategorySum(String category,BigDecimal totalAmount){
        this.category=category;
        this.totalAmount=totalAmount;
    }

}
