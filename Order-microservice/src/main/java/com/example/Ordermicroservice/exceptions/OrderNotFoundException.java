package com.example.Ordermicroservice.exceptions;

import jakarta.persistence.criteria.Order;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(){
        super(
                "Given Resource is not available"
        );
    }

    public OrderNotFoundException(String msg){
        super(msg);
    }

}
