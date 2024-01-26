package com.example.Ordermicroservice.service;

import com.example.Ordermicroservice.model.OrderDetails;

import java.util.List;

public interface OrderService {

    OrderDetails createOrder(OrderDetails orderDetails);

    List<OrderDetails> getAllOrders();

    OrderDetails getOrderById(long orderId);

    List<OrderDetails> getAllUsingProductId(long productId);

}
