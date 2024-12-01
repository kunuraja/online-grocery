package com.raj.onlinegrocery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {
    private LocalDateTime orderDate;
    List<Long> groceryIds;
}
