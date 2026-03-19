package com.duact.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.duact.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	List<OrderItem> findByOrderId(Long orderId);
	
}
