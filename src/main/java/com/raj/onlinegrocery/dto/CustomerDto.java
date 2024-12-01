package com.raj.onlinegrocery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String name;
    private String email;
    private String address;
    private int phone;
}