package com.raj.onlinegrocery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryDto {
    private String name;
    private String category;
    private Double price;
    private int quantity;
}
