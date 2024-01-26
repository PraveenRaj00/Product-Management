package com.example.Productmicroservice.service;

import com.example.Productmicroservice.entity.OrderDetails;
import com.example.Productmicroservice.entity.Product;
import com.example.Productmicroservice.repository.ProductRepository;
import com.sun.source.tree.ModuleTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductServiceImplTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    static Product product1;
    static Product product2;
    static List<Product> productList= new ArrayList<>();

    @BeforeAll
    public static void beforeAllMethod(){

        productList.add(product1);
        productList.add(product2);
        product1= Product.builder()
                .productId(11)
                .productName("Realme 11 pro plus 5g")
                .price(25000)
                .category("Mobile")
                .quantity(30)
                .build();
        product2= Product.builder()
                .productId(8)
                .productName("Mi Note 13 pro plus 5g")
                .price(30000)
                .category("Mobile")
                .quantity(40)
                .build();
    }

    @Test
    public void getSingleProductsTest(){
        Mockito.when(productRepository.findById(11L)).thenReturn(Optional.ofNullable(product1));
        Product expectedProductName= productService.getSingle(11);
        Assertions.assertEquals(expectedProductName.getProductName(),product1.getProductName());
    }

    @Test
    public void getAllTest(){
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        Assertions.assertEquals(2,productList.size());
    }

    @Test
    public void updateValueTest(){
        Mockito.when(productRepository.findById(11L)).thenReturn(Optional.of(product1));
        Product newProduct= product1;
        newProduct.setPrice(24999.0);
        Mockito.when(productRepository.save(newProduct)).thenReturn(product1);
        Assertions.assertEquals(product1.getPrice(),newProduct.getPrice());
    }

    @Test
    public void deleteByIdTest(){
        String result= productService.deleteProduct(11L);
        Assertions.assertEquals("Deleted Successfully !",result);

    }

    @AfterAll
    public static void afterAllTest(){
        System.out.println("To perform something after running all test cases : ");

    }


}
