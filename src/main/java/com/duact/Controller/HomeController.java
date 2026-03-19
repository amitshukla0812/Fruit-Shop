package com.duact.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.duact.DTO.Cart_Add_DTO;
import com.duact.DTO.View_User_Product;
import com.duact.Service.ProductService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
    @GetMapping("/")
    public String home(Model model) {
    	
    	 model.addAttribute("products", productService.getHomeProducts());
        return "index";  
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/news")
    public String news() {
        return "news";
    }

    @GetMapping("/single-news")
    public String singleNews() {
        return "single-news";
    }
   
    @GetMapping("/Shop-Product")
    public String shop(Model model) {
        List< View_User_Product> products = productService.getProductsForUser();
        model.addAttribute("products", products);
        return "Shop-Product";
    }
    
    
     @GetMapping("/ProductOnUser")
    public String product(Model model) {
        List< View_User_Product> products = productService.getProductsForUser();
        model.addAttribute("products", products);
        return "ProductOnUser";
    }
    
    
    
    
}
