package com.duact.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duact.Entity.Order;
import com.duact.Entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

    
	List<Order> findByEmail(String email);
}
