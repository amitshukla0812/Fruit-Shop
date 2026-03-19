package com.duact.Service;

import java.util.List;

import com.duact.DTO.Cart_Add_DTO;
import com.duact.Entity.Cart;
import com.duact.Entity.Product;

public interface CartService {

    //  Product ko cart me add karega 
    void addToCart(Long productId);

    //  Cart ke saare items list karega (DTO ke through)
    List<Cart_Add_DTO> getCartItems();

    //  Quantity badhane ke liye
    void increaseQuantity(Long id);

    //  Quantity kam karne ke liye
    void decreaseQuantity(Long id);

    //  Cart se product hataane ke liye
    void removeItem(Long id);

    double getTotalPrice();

	
}
