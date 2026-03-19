package com.duact.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.duact.Entity.Order;
import com.duact.Entity.OrderItem;
import com.duact.Entity.User;
import com.duact.Service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    // PLACE ORDER
    @GetMapping("/order-success")
    public String placeOrder(HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        orderService.placeOrder(user.getEmail());
        return "order-success";
    }

    // ORDER HISTORY
    @GetMapping("/my-orders")
    public String myOrders(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        List<Order> orders = orderService.getOrders(user.getEmail());
        model.addAttribute("orders", orders);

        return "my-orders";
    }

    // ORDER DETAILS
    @GetMapping("/order-items/{id}")
    public String orderItems(@PathVariable Long id, Model model) {

        List<OrderItem> items = orderService.getOrderItems(id);
        model.addAttribute("items", items);

        return "order-items";
    }
}
