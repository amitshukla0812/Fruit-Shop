package com.duact.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duact.Entity.Cart;
import com.duact.Entity.Product;
import com.duact.Service.CartService;
import com.duact.Service.CartServiceImpl;
import com.duact.Service.ProductService;

import jakarta.servlet.http.HttpSession;


@Controller
public class CartController {

    @Autowired
    private CartService cartService;


    //  Add product to cart
    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id) {

        cartService.addToCart(id);

        return "redirect:/ProductOnUser?success";
    }


    //  View cart
    @GetMapping("/Add-Cart")
    public String viewCart(Model model) {

        model.addAttribute("cartItems", cartService.getCartItems());

        return "Add-Cart";
    }
    
     //  Increase quantity
    @GetMapping("/cart/increase/{id}")
    public String increaseQuantity(@PathVariable Long id) {

        cartService.increaseQuantity(id);

        return "redirect:/Add-Cart";
    }


    //  Decrease quantity
    @GetMapping("/cart/decrease/{id}")
    public String decreaseQuantity(@PathVariable Long id) {

        cartService.decreaseQuantity(id);

        return "redirect:/Add-Cart";
    }


    //  Remove item
    @GetMapping("/cart/delete/{id}")
    public String removeItem(@PathVariable Long id) {

        cartService.removeItem(id);

        return "redirect:/Add-Cart";
    }
}
