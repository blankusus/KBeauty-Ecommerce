package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.Wishlist;
import com.example.mvcproducts.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface WishlistRepository extends CrudRepository<Wishlist, Long> {
    Wishlist findByUser(User user);
}

