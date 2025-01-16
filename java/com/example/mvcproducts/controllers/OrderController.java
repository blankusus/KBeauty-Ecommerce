package com.example.mvcproducts.controllers;

import org.springframework.ui.Model;

import com.example.mvcproducts.domain.ProductOrder;
import com.example.mvcproducts.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String viewOrders(Model model) {
        List<ProductOrder> orders = orderService.findAllOrders();
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping("/{id}")
    public String viewOrderDetails(@PathVariable Long id, Model model) {
        ProductOrder order = orderService.findOrderById(id); // Add a method in the service layer
        model.addAttribute("order", order);
        return "order-details";
    }

}
