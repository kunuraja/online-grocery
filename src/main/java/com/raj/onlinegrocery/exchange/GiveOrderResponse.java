package com.raj.onlinegrocery.exchange;

import com.raj.onlinegrocery.entity.GroceryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiveOrderResponse {
    private Long orderId;
    private LocalDate orderDate;
    private Double price;
    private List<GroceryEntity> groceries = new ArrayList<>();
}
