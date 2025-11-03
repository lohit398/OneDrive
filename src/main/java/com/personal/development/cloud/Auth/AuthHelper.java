package com.personal.development.cloud.Auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.personal.development.cloud.Entities.User;
import com.personal.development.cloud.Repositories.UserRepository;


@Component
public class AuthHelper {

    @Autowired
    private UserRepository repo;
    
    public String generateToken(String username) {
        // Token generation logic
        return "jwt-token-for-" + username;
    }
    
    public boolean validateToken(String token) {
        // Token validation logic
        return token != null && token.startsWith("jwt-token");
    }
    
    public String hashPassword(String password) {
        // Password hashing logic
        return "hashed-" + password;
    }

    public boolean validateUserNamePassword(String userName,String passwordHash){
        Optional<User> optionalUser = repo.findByUsername(userName);
        if(optionalUser.isEmpty())
            return false;

        
        User user = optionalUser.get();
        if(passwordHash.equals(user.getPasswordHash())){
            return true;
        }
        return false;
    }
}