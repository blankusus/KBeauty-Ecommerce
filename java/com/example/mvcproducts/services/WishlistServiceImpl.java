package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Product;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.domain.Wishlist;
import com.example.mvcproducts.repositories.WishlistRepository;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist getWishlistByUser(User user) {
        return wishlistRepository.findByUser(user);
    }

    @Override
    public void addProductToWishlist(User user, Product product) {
        Wishlist wishlist = getWishlistByUser(user);
        if (wishlist == null) {
            wishlist = createWishlistForUser(user);
        }
        wishlist.addProduct(product);
        wishlistRepository.save(wishlist);
    }

    @Override
    public void removeProductFromWishlist(User user, Product product) {
        Wishlist wishlist = getWishlistByUser(user);
        if (wishlist != null) {
            wishlist.removeProduct(product);
            wishlistRepository.save(wishlist);
        }
    }

    @Override
    public Wishlist createWishlistForUser(User user) {
        Wishlist wishlist = new Wishlist(user);
        return wishlistRepository.save(wishlist);
    }
}

