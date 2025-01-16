package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Cart;
import com.example.mvcproducts.domain.OrderLineItem;
import com.example.mvcproducts.domain.ProductOrder;
import com.example.mvcproducts.services.OrderService;
import com.example.mvcproducts.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@SessionAttributes("cart")
@RequestMapping("/cart")
public class CartController {

    @ModelAttribute("cart")
    public Cart initializeCart() {
        return new Cart();
    }
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long pid, @ModelAttribute("cart") Cart cart, RedirectAttributes redirectAttributes) {
        productService.findById(pid).ifPresentOrElse(
                product -> {
                    cart.addProduct(product);
                    redirectAttributes.addFlashAttribute("success", "Product added to cart successfully!");
                },
                () -> redirectAttributes.addFlashAttribute("error", "Product not found!")
        );
        return "redirect:/products";
    }

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

    @GetMapping
    public String seeCart(Model model, @ModelAttribute("cart") Cart cart) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/checkout-form")
    public String checkoutForm(Model model, @ModelAttribute("cart") Cart cart) {
        model.addAttribute("cart", cart);
        model.addAttribute("total", cart.getTotalPrice());
        return "checkout-form";
    }



    @PostMapping("/complete-checkout")
    public String completeCheckout(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String phoneNumber,
            @RequestParam String address,
            @RequestParam String postalCode,
            @RequestParam String paymentMethod,
            @RequestParam(required = false) String cardNumber,
            Model model,
            @ModelAttribute("cart") Cart cart
    ) {
        if (cart.getItems().isEmpty()) {
            model.addAttribute("errorMessage", "Your cart is empty!");
            return "checkout-form";
        }

        try {
            ProductOrder productOrder = new ProductOrder();
            Set<OrderLineItem> orderItems = new HashSet<>(cart.getItems());

            productOrder.setOrderLineItems(orderItems);
            productOrder.setFirstName(firstName);
            productOrder.setLastName(lastName);
            productOrder.setPhoneNumber(phoneNumber);
            productOrder.setAddress(address);
            productOrder.setPostalCode(postalCode);
            productOrder.setPaymentMethod(paymentMethod);

            if ("Card".equalsIgnoreCase(paymentMethod)) {
                productOrder.setCardNumber(cardNumber);
            }

            orderService.save(productOrder);
            cart.clearItems(); // Clear the cart after saving
            model.addAttribute("successMessage", "Order placed successfully!");
            return "checkout-success";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An error occurred while placing your order.");
            return "checkout-form";
        }
    }



    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long pid, @ModelAttribute("cart") Cart cart, RedirectAttributes redirectAttributes) {
        cart.removeProduct(pid);
        redirectAttributes.addFlashAttribute("success", "Product removed from cart successfully!");
        return "redirect:/cart";
    }

}
