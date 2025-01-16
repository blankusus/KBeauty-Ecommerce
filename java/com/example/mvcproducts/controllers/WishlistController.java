package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Cart;
import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.ProductService;
import com.example.mvcproducts.services.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
@SessionAttributes("cart")
public class WishlistController {

    private final WishlistService wishlistService;
    private final ProductService productService;

    public WishlistController(WishlistService wishlistService, ProductService productService) {
        this.wishlistService = wishlistService;
        this.productService = productService;
    }

    @GetMapping
    public String viewWishlist(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        // Check if the wishlist exists; if not, create a new one
        var wishlist = wishlistService.getWishlistByUser(user);
        if (wishlist == null) {
            wishlist = wishlistService.createWishlistForUser(user);
        }
        model.addAttribute("wishlist", wishlist);
        return "wishlist";
    }

    @PostMapping("/add")
    public String addToWishlist(@RequestParam Long productId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        productService.findById(productId).ifPresent(product -> wishlistService.addProductToWishlist(user, product));
        return "redirect:/products";
    }

    @PostMapping("/remove")
    public String removeFromWishlist(@RequestParam Long productId, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        productService.findById(productId).ifPresent(product -> wishlistService.removeProductFromWishlist(user, product));
        return "redirect:/wishlist";
    }

    @PostMapping("/add-to-cart")
    public String addToCartFromWishlist(@RequestParam Long productId, Authentication authentication, @ModelAttribute("cart") Cart cart) {
        User user = (User) authentication.getPrincipal();
        productService.findById(productId).ifPresent(product -> {
            cart.addProduct(product); // Add the product to the cart
            wishlistService.removeProductFromWishlist(user, product); // Optionally remove from wishlist
        });
        return "redirect:/cart"; // Redirect to the cart page
    }
}
