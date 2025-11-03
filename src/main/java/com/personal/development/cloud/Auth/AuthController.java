package com.personal.development.cloud.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        // Generate a JWT and send it to the front end
        Boolean recordFound = this.authHelper.validateUserNamePassword(username, passwordHash);
        if(recordFound){
            // jwt generation
            return "Login Successfull";
        }
        else return "Please check your username & password.";
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String username) {
        // Logic for user logout
        // Destroy the session
        return "Logout successful for user: " + username;
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        // Logic for user registration
        // Register the user
        return "Registration successful for user: " + username;
    }

    @PostMapping("validate")
    public boolean validateToken(@RequestParam String token) {
        // Logic to validate the authentication token
        // Validate the JWT and get the role of the user
        return true; // Placeholder for actual validation logic
    }
}