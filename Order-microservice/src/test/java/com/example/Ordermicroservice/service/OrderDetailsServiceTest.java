package com.example.Ordermicroservice.service;

import com.example.Ordermicroservice.model.OrderDetails;
import com.example.Ordermicroservice.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderDetailsServiceTest {

    //Autowiring OrderService
    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    OrderDetails orderDetails1;
    OrderDetails orderDetails2;

    static List<OrderDetails> orderDetailsList= new ArrayList<>();

    @BeforeEach
    public void setup(){
        orderDetailsList.add(orderDetails1);
        orderDetailsList.add(orderDetails2);

        orderDetails1= OrderDetails.builder()
                .orderId(3)
                .productId(1)
                .quantity(3)
                .totalPrice(90000)
                .build();
        orderDetails2= OrderDetails.builder()
                .orderId(4)
                .productId(2)
                .quantity(2)
                .totalPrice(60000)
                .build();
    }

    @Test
    public void getOrderById(){
        Mockito.when(orderRepository.findById(orderDetails1.getOrderId())).thenReturn(Optional.of(orderDetails1));
        OrderDetails actualResult= orderService.getOrderById(3L);
        double expectedResult= orderDetails1.getTotalPrice();
        Assertions.assertEquals(expectedResult,actualResult.getTotalPrice());
    }


}
