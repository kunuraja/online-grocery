package com.raj.onlinegrocery.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderResponse {
    private Long customerId;
    private List<Long> remainingGroceryIds = new ArrayList<>();
}
