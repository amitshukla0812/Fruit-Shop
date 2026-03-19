package com.duact.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duact.DTO.View_User_Product;
import com.duact.Entity.Product;
import com.duact.Repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

  

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    

    @Override
    public List<View_User_Product> getProductsForUser() {

        List<Product> products = productRepository.findAll();
        List<View_User_Product> dtoList = new ArrayList<>();

        for (Product p : products) {
            dtoList.add(new View_User_Product(
                    p.getId(),
                    p.getName(),
                    p.getPrice(),
                    p.getImageFile(),   
                    p.getDescription()
            ));
        }
        return dtoList;
    }

    
    @Override
    public List<View_User_Product> getHomeProducts() {

        List<Product> products = productRepository.findTop3ByShowOnHomeTrue();
        List<View_User_Product> dtoList = new ArrayList<>();

        for (Product p : products) {
            dtoList.add(new View_User_Product(
                    p.getId(),
                    p.getName(),
                    p.getPrice(),
                    p.getImageFile(),
                    p.getDescription()
            ));
        }
        return dtoList;
    }
}
