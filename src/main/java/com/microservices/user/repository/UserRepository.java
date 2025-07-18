package com.microservices.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.microservices.user.entity.Users;


public interface UserRepository {
	public void saveUserDetails(Users user);
	public Integer updateUserDetails(Users user);
	public  List<Users> getUsers();
	public Optional<Users> findByUserName(String userName);
	public boolean checkUserExistOrNot(String userName);
}
