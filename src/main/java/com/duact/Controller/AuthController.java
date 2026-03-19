package com.duact.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duact.Entity.User;
import com.duact.Repository.UserRepository;
import com.duact.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

	
	
    @Autowired
    private UserRepository userRepo;
    @Autowired
    
    private UserService userService;
   
   
    
    @GetMapping("/register")
    public String registerPage(Model model) {
       model.addAttribute("user",new User());
    		return "register";
    }
    
    @PostMapping("/register")
    public String submitRegForm(@ModelAttribute("user") User user, Model model) {
	boolean status =userService.registerUser(user);
	
	if(status)
	{
		model.addAttribute("successmessage","user Registerd Succesfully");
	}
	else
	{
		model.addAttribute("errormessage","user not registered");
	}
	 	return "register";
    }

    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage(Model model) {
    	 model.addAttribute("user",new User());
    	return "login";
    }
      
    
    @PostMapping("/login")
    public String SubmitUserLogin(
            @ModelAttribute("user") User user,
            Model model,
            HttpServletRequest request) {

        // ADMIN LOGIN
        if ("admin@gmail.com".equals(user.getEmail()) &&
            "admin123".equals(user.getPassword())) {

            return "admin/admin-dashboard";
        }

        User validUser = userService.loginUser(user.getEmail(), user.getPassword());

        if (validUser != null) {

            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", validUser); 

            return "user/user-dashboard";
        } else {
            model.addAttribute("errorMessage", "Email or password wrong");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
    	
    	HttpSession session = request.getSession(false);
    	if(session !=  null)
    	{
    		session.invalidate();
    	}
    	return "redirect:/login";
    }
    } 
    

