package com.microservices.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.microservices.user.entity.Users;
import com.microservices.user.entity.Users;
import com.microservices.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/api")//user/api/saveUserDetails
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/saveUserDetails", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;")
    public ResponseEntity<String> saveUserDetails(@RequestBody Users user) {
      String response =   userService.saveUserDetails(user);
        return ResponseEntity.ok(response);
    }
    
    
    @RequestMapping(value = "/updateUserDetails", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> updateUserDetails(@RequestBody Users user) {
        boolean isUpdated = userService.updateUserDetails(user);
        if (isUpdated) {
            return ResponseEntity.ok("User details updated successfully.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }
    @GetMapping(value = "/getUserDetails", produces = "application/json")
    public ResponseEntity<?> getUserDetails() {
        List<Users> listOfUsers = userService.getUserDetails();
        
        if (listOfUsers != null && !listOfUsers.isEmpty()) {
            return ResponseEntity.ok(listOfUsers); // Spring Boot will automatically convert it to JSON
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Users not found.");
        }
    }
    
    

}
