package com.duact.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duact.Entity.User;
import com.duact.Repository.UserRepository;
@Service
public interface UserService {

	public boolean registerUser(User user);
	
	public User loginUser(String email,String password );
	
	public List<User> getAllUser();

	public void deleteProduct(Long id);


}
