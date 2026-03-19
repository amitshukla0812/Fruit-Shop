package com.duact.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duact.DTO.Cart_Add_DTO;
import com.duact.Entity.Cart;
import com.duact.Entity.Product;
import com.duact.Repository.CartRepository;
import com.duact.Repository.ProductRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    @Override
    public void addToCart(Long productId) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

  
        Cart cart = cartRepo.findByName(product.getName()).orElse(null);

        if (cart != null) {
        
            cart.setQuantity(cart.getQuantity() + 1);
        } else {
         
            cart = new Cart();
            cart.setName(product.getName());
            cart.setPrice(product.getPrice());
            cart.setQuantity(1);

            
            if (product.getImageFile() != null) {
                cart.setImageFile(product.getImageFile());
            } else {
                cart.setImageFile("no-image.png");
            }
        }

        cartRepo.save(cart);
    }

    @Override
    public List<Cart_Add_DTO> getCartItems() {
        return cartRepo.findAll().stream()
                .map(cart -> new Cart_Add_DTO(
                        cart.getId(),
                        cart.getName(),
                        cart.getImageFile(),
                        cart.getQuantity(),
                        cart.getPrice()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void increaseQuantity(Long id) {
        Cart cart = cartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        cart.setQuantity(cart.getQuantity() + 1);
        cartRepo.save(cart);
    }

    @Override
    public void decreaseQuantity(Long id) {
        Cart cart = cartRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (cart.getQuantity() > 1) {
            cart.setQuantity(cart.getQuantity() - 1);
            cartRepo.save(cart);
        }
    }

    @Override
    public void removeItem(Long id) {
        cartRepo.deleteById(id);
    }

    @Override
    public double getTotalPrice() {
        return cartRepo.findAll()
                .stream()
                .mapToDouble(Cart::getTotalPrice)
                .sum();
    }
}
