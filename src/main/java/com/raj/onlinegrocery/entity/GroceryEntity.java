package com.raj.onlinegrocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "grocery")
public class GroceryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goroceryId;
    private String name;
    private String category;
    private Double price;
    private int quantity;

}
