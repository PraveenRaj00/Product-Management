package com.example.Ordermicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private long productId;
    private String productName;
    private String category;
    private double price;
    private long quantity;

}
