package com.skillbridge.backend.service;

import com.skillbridge.backend.dto.AuthResponse;
import com.skillbridge.backend.dto.LoginRequest;
import com.skillbridge.backend.dto.RegisterRequest;
import com.skillbridge.backend.model.User;
import com.skillbridge.backend.model.UserRole;
import com.skillbridge.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

        return new AuthResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole());
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return new AuthResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole());
    }

}