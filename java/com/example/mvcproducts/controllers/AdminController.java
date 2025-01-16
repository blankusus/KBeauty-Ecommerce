package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  private final ProductService productService;

  public AdminController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public String showAdminPanel(Model model) {
    // Add a list of all products to the model for display
    model.addAttribute("products", productService.findAll());
    // Add an empty product object for the add-product form
    model.addAttribute("product", new Product());
    return "admin"; // Return the admin HTML template
  }
}
