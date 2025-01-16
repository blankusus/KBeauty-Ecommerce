package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.ProductOrder;

import java.util.List;

public interface OrderService {
    void save(ProductOrder productOrder);
    List<ProductOrder> findAllOrders(); // Fetch all orders
    ProductOrder findOrderById(Long id); // Find order by ID
}
