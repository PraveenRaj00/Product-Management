package com.example.Ordermicroservice.service;

import com.example.Ordermicroservice.config.FeignClientConfig;
import com.example.Ordermicroservice.exceptions.OrderNotFoundException;
import com.example.Ordermicroservice.model.OrderDetails;
import com.example.Ordermicroservice.model.Product;
import com.example.Ordermicroservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class orderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository repository;

    @Autowired
    private FeignClientConfig config;

    @Override
    public OrderDetails createOrder(OrderDetails orderDetails) {
        return repository.save(orderDetails);
    }

    @Override
    public List<OrderDetails> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public OrderDetails getOrderById(long orderId) {
        return repository.findById(orderId).orElseThrow(
                ()-> new OrderNotFoundException()
        );
    }

    @Override
    public List<OrderDetails> getAllUsingProductId(long productId) {
        return repository.findByProductId(productId);
    }
}
