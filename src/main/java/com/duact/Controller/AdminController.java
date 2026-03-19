package com.duact.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duact.Entity.Contact;
import com.duact.Service.ContactService;
import com.duact.Service.OrderService;
import com.duact.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller

public class AdminController {

    
	@Autowired
    private UserService userService;
	
	@Autowired
	private OrderService orderService;
	 @Autowired
	    private ContactService contactService;

    @GetMapping("/admin/user-data")
    public String adminDashboard(Model model) {
        model.addAttribute("user", userService.getAllUser());
        return "admin/user-data";
    }
    
    @GetMapping("/admin/delete-Register/{id}")
    public String deleteProduct(@PathVariable("id")  Long id) {
        userService.deleteProduct(id);
        return "redirect:/admin/user-data";
    }
    

    @GetMapping("/admin/Contact-Data")
    public String contactData(Model model) {

        model.addAttribute("contacts", contactService.getAllContacts());
        return "Contact-Data";
    }

    @PostMapping("/contact/save")
    public String saveContact(@ModelAttribute Contact contact) {

        contactService.saveContact(contact);
        return "redirect:/contact?success";
    }
    
    @GetMapping("/admin/messagedelete/{id}")
    public String deleteContact(@PathVariable("id")  Long id) {
    	contactService.deleteContact(id);
        return "Contact-Data";
    }
    
    @GetMapping("/admin/Admin-My-order")
    public String adminOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrdersForAdmin());
        return "admin/Admin-My-order";
    }
    @GetMapping("/admin/Order-delete/{id}")
    public String deleteOrder(@PathVariable("id")  Long id) {
    	orderService.deleteOrder(id);
        return "redirect:/admin/Admin-My-order";
    }
}
