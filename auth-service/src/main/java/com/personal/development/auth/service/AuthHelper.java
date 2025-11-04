package com.personal.development.auth.service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.personal.development.auth.entity.User;
import com.personal.development.auth.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.personal.development.auth.service.RedisService;


@Component
public class AuthHelper {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RedisService redisService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(User user) {
        Instant now = Instant.now();
        Instant expiration = now.plusMillis(jwtExpiration);

        // updating the token to redis before sending it.

        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .claim("Role",user.getRole())
                .compact();

        this.redisService.setValueWithExpiry(token,false,jwtExpiration); 

        return token;
    }

    public boolean validateToken(String token) {
        if(token == null || token.length() == 0)
            return false;
        // Token validation logic
        return token != null && token.startsWith("jwt-token");
    }

    public String hashPassword(String password) {
        // Password hashing logic
        return "hashed-" + password;
    }

    public Optional<User> validateUserNamePassword(String userName,String passwordHash){
        Optional<User> optionalUser = repo.findByUsername(userName);
        if(optionalUser.isEmpty())
            return Optional.empty();
        User user = optionalUser.get();
        if(passwordHash.equals(user.getPasswordHash())){
            return optionalUser;
        }
        return Optional.empty();
    }
}
