package com.duact.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duact.Entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	public User save(User user);

	public User findByEmail(String email);

	
	
}
