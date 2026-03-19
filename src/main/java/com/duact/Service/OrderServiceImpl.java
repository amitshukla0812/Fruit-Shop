package com.duact.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duact.DTO.Cart_Add_DTO;
import com.duact.DTO.Order_DTO;
import com.duact.Entity.Cart;
import com.duact.Entity.Order;
import com.duact.Entity.OrderItem;
import com.duact.Entity.User;
import com.duact.Repository.OrderItemRepository;
import com.duact.Repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository itemRepo;

    @Autowired
    private CartService cartService;

    @Override
    @Transactional   
    public void placeOrder(String email) {

        List<Cart_Add_DTO> cartList = cartService.getCartItems();

        double totalPrice = 0;

        Order order = new Order();
        order.setEmail(email);
        order.setStatus("PLACED");

       
        for (Cart_Add_DTO c : cartList) {
            totalPrice += c.getPrice() * c.getQuantity();
        }

        order.setTotalPrice(totalPrice);

       
        orderRepo.save(order);

        
        for (Cart_Add_DTO c : cartList) {

            OrderItem item = new OrderItem();
            item.setName(c.getName());
            item.setPrice(c.getPrice());
            item.setQuantity(c.getQuantity());

            item.setOrder(order);   

            itemRepo.save(item);
        }

       
    }

    @Override
    public List<Order> getOrders(String userEmail) {
        return orderRepo.findByEmail(userEmail);
    }

    @Override
    public List<OrderItem> getOrderItems(Long orderId) {
        return itemRepo.findByOrderId(orderId);
    }

    @Override
    public List<Order_DTO> getAllOrdersForAdmin() {

        List<Order> orders = orderRepo.findAll();
        List<Order_DTO> dtoList = new ArrayList<>();

        for (Order o : orders) {
            dtoList.add(new Order_DTO(
                    o.getId(),
                    o.getEmail(),
                    o.getTotalPrice(),
                    o.getStatus()
            ));
        }
        return dtoList;
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
}
