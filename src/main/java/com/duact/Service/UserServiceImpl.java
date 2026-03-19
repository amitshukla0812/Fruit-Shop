package com.duact.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duact.Entity.User;
import com.duact.Repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private  UserRepository UserRepo;

	@Override
	public boolean registerUser(User user) {
		
		try {
			UserRepo.save(user);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		}
	
	
	public User loginUser(String email,String password ) {
    	
		   User user= 	UserRepo.findByEmail(email);
		   
		   if(user!= null && user.getPassword().equals(password)) {
			   return user;
		   }
		    	return null ;
		    }
	
	 public List<User> getAllUser() {
	        return UserRepo.findAll();
	    }


	 @Override
	 public void deleteProduct(Long id) {
		UserRepo.deleteById(id);
		
	 }
	
}
