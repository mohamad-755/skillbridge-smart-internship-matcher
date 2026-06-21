package com.skillbridge.backend.security;

import com.skillbridge.backend.model.User;
import com.skillbridge.backend.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class AuthContext {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthContext(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public User getUserFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Missing authorization token");
        }

        String token = authorizationHeader.substring(7);
        Claims claims = jwtService.extractClaims(token);

        Integer userId = claims.get("userId", Integer.class);

        return userRepository.findById(userId)
                .orElseThrow(() -> new SecurityException("Invalid authorization token"));
    }
}