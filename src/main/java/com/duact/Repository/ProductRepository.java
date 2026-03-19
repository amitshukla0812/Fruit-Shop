package com.duact.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duact.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	
   
	 List<Product> findTop3ByShowOnHomeTrue();
}
