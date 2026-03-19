package com.duact.Service;

import java.util.List;

import com.duact.DTO.View_User_Product;
import com.duact.Entity.Product;

public interface ProductService {

   
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Long id);

    
    List<View_User_Product> getProductsForUser();

    List<View_User_Product> getHomeProducts();
}
