package com.microservices.user.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.microservices.user.entity.Users;

public interface UserService extends UserDetailsService {
	public String saveUserDetails(Users user);
	public boolean updateUserDetails(Users user);
	public List<Users> getUserDetails();
	public UserDetails loadUserByUsername(String username);
}
