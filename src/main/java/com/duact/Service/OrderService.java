package com.duact.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duact.DTO.Order_DTO;
import com.duact.Entity.Order;
import com.duact.Entity.OrderItem;
import com.duact.Entity.User;


@Service
public interface OrderService {

  public 	void placeOrder(String email);

  public   List<Order> getOrders(String email);

  public  List<OrderItem> getOrderItems(Long orderId);
  
  List<Order_DTO> getAllOrdersForAdmin();

  public void deleteOrder(Long id);

}

