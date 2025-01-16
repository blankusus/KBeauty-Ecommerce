package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.domain.Wishlist;

public interface WishlistService {
    Wishlist getWishlistByUser(User user);
    void addProductToWishlist(User user, Product product);
    void removeProductFromWishlist(User user, Product product);
    Wishlist createWishlistForUser(User user);
}
