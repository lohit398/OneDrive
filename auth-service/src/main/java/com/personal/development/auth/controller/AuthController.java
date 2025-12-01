package com.personal.development.auth.controller;

import java.net.InetAddress;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.development.auth.entity.User;
import com.personal.development.auth.service.AuthHelper;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthHelper authHelper;

    @Autowired
    public AuthController(AuthHelper authHelper) {
        this.authHelper = authHelper;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String passwordHash) {
        // Logic for user login
        // Generate a JWT
        // Insert the JWT into redis key (JWT), value is a boolean if it is blacklisted
        try{
            System.out.println("Worker working on this request "+InetAddress.getLocalHost().getHostName());
        }catch(Exception e){
            System.out.println("Unable to resolve worker");
        }
        Optional<User> userRec= this.authHelper.validateUserNamePassword(username, passwordHash);
        if(!userRec.isEmpty()){
            return this.authHelper.generateToken(userRec.get());
        }
        else return "Please check your username & password.";
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String username) {
        // Logic for user logout
        // blacklist the token with [Redis]
        
        return "Logout successful for user: " + username;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {

        // Logic for user registration
        // Create user record
        // Send an email for email confirmation
        // Issue a JWT

        return "Registration successful for user: " + username;
    }

    @PostMapping("validate")
    public boolean validateToken(@RequestParam String token) {
        // it should be from the header
        // Logic to validate the authentication token
        // Validate the JWT and get the role of the user
        return true; // Placeholder for actual validation logic
    }
}
