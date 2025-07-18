package com.microservices.user.service;
import java.util.Collections;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.microservices.user.entity.Users;
import com.microservices.user.repository.UserRepository;
@Service
@Primary 
@Transactional
public class UserServiceImpl implements UserService {
@Autowired
private UserRepository userRepository;

@Autowired
private PasswordEncoder passwordEncoder;


    @Transactional("mysqlTransactionManager")
	public String saveUserDetails(Users user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		boolean existOrNot  = userRepository.checkUserExistOrNot(user.getUserName());
		if(existOrNot==true) {
		  return "exist";	
		}else {
		  userRepository.saveUserDetails(user);
		  return "success";
		}
		
	}
    
	@Override
	@Transactional("mysqlTransactionManager")
	public boolean updateUserDetails(Users user) {	
		Integer userId = userRepository.updateUserDetails(user);
		if(userId!=null) {
			return true;
		}else {
			return false;
		}
				
	}
	@Override
	@Transactional("mysqlTransactionManager")
	public List<Users> getUserDetails() {
		List<Users> listOfata  = userRepository.getUsers();
		return userRepository.getUsers();
	}
	
	 @Override
	 @Transactional("mysqlTransactionManager")
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Users user = userRepository.findByUserName(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

	        return new User(user.getUserName(), user.getUserPassword(),
	                Collections.emptyList()); 
	  }

}
