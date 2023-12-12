package com.meow.dtos.response;

import com.meow.entities.Category;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private BigDecimal totalMoney;

    public CategoryResponse(Category category){
        BeanUtils.copyProperties(category,this);
    }
}
