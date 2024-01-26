package com.example.Ordermicroservice.controller;

import com.example.Ordermicroservice.config.FeignClientConfig;
import com.example.Ordermicroservice.model.OrderDetails;
import com.example.Ordermicroservice.model.Product;
import com.example.Ordermicroservice.repository.OrderRepository;
import com.example.Ordermicroservice.service.OrderService;
import jakarta.persistence.criteria.Order;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private FeignClientConfig config;

    @Autowired
    OrderService service;



    @PostMapping
    public ResponseEntity<OrderDetails> createOrder(@RequestBody OrderDetails orderDetails){

        Product product= config.getProduct(orderDetails.getProductId());

        double price= product.getPrice() * orderDetails.getQuantity();

        orderDetails.setTotalPrice(price);
        service.createOrder(orderDetails);

        orderDetails.setProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDetails);
    }

    @GetMapping
    public ResponseEntity<List<OrderDetails>> getAll(){

        return ResponseEntity.ok(service.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetails> getOneOrder(@PathVariable long orderId){

        OrderDetails newProduct= service.getOrderById(orderId);
        Product product= config.getProduct(newProduct.getProductId());

        newProduct.setProduct(product);

        return ResponseEntity.ok(newProduct);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderDetails>> getUsingProductId(@PathVariable long productId){
        return ResponseEntity.ok(service.getAllUsingProductId(productId));
    }

}
