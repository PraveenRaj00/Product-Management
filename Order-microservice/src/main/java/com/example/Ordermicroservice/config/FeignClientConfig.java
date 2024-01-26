package com.example.Ordermicroservice.config;

import com.example.Ordermicroservice.model.Product;
import jakarta.ws.rs.Path;
import lombok.Getter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "PRODUCT-SERVICE")
@Service
public interface FeignClientConfig {

    @GetMapping("/products/{productId}")
    Product getProduct(@PathVariable long productId);



}
