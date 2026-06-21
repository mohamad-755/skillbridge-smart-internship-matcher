package com.skillbridge.backend.service;

import com.skillbridge.backend.dto.AuthResponse;
import com.skillbridge.backend.dto.LoginRequest;
import com.skillbridge.backend.dto.RegisterRequest;
import com.skillbridge.backend.model.User;
import com.skillbridge.backend.model.UserRole;
import com.skillbridge.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.skillbridge.backend.security.JwtService;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        UserRole role = request.getRole() != null ? request.getRole() : UserRole.STUDENT;

        User user = new User(
                null,
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                role);

        User savedUser = userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getRole(), token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(user.getId(), user.getName(), user.getEmail(), user.getRole(), token);
    }

}