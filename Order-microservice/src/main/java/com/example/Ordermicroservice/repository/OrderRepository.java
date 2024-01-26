package com.example.Ordermicroservice.repository;

import com.example.Ordermicroservice.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Long> {

    List<OrderDetails> findByProductId(long productId);

}
