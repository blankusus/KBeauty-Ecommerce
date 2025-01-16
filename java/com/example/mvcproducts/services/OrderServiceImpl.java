package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.ProductOrder;
import com.example.mvcproducts.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;

  @Autowired
  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void save(ProductOrder productOrder) {
    orderRepository.save(productOrder);
  }

  @Override
  public List<ProductOrder> findAllOrders() {
    return (List<ProductOrder>) orderRepository.findAll(); // Fetch all orders
  }

  @Override
  public ProductOrder findOrderById(Long id) {
    return orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found"));
  }
}
