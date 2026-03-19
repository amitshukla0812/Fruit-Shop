package com.duact.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duact.Entity.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {
 

    Optional<Cart> findByName(String name);
}
