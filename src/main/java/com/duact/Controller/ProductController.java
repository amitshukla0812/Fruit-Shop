package com.duact.Controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.duact.Entity.Product;
import com.duact.Repository.ProductRepository;
import com.duact.Service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // 🔹 Product Table View
    @GetMapping("/admin/productList")
    public String productList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        
        return "admin/productList";
    }

    // 🔹 Add Form
    @GetMapping("/admin/add_product")
    public String addForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/add_product";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("imageFile"); // 🔥 MAIN FIX
    }
    
    
    @PostMapping("/admin/save")
    public String saveProduct(
            @ModelAttribute Product product,
            @RequestParam("imageFile") MultipartFile file
    ) throws IOException {


        if (file != null && !file.isEmpty()) {

            
            String imageFileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String uploadDir = "D:/springboot-Image/";
             Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

   
            Path imagePath = uploadPath.resolve(imageFileName);
            Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);

            // DB me sirf image name save
            product.setImageFile(imageFileName);
        }

        //  product save
        productService.saveProduct(product);

        return "redirect:/admin/add_product?success";
    }

       @GetMapping("/admin/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/Edit-Product";  
    }

    
    @PostMapping("/admin/edit")
    public String updateProduct(
            @ModelAttribute Product product,
            @RequestParam(value = "imageFile", required = false) MultipartFile file
    ) throws IOException {

      
        Product existingProduct = productService.getProductById(product.getId());

        if (existingProduct == null) {
            return "redirect:/admin/productList";
        }
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setShowOnHome(product.isShowOnHome());


        if (file != null && !file.isEmpty()) {

            String fileName = file.getOriginalFilename();

            String uploadDir = "D:/springboot-Image/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            existingProduct.setImageFile(fileName);
        }
       

        productService.saveProduct(existingProduct);

        return "redirect:/admin/productList";
    }


    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable("id")  Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/productList";
    }
}
