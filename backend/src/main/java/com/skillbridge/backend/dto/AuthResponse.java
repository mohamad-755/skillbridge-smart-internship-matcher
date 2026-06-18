package com.skillbridge.backend.dto;

import com.skillbridge.backend.model.UserRole;

public class AuthResponse {
    private Integer id;
    private String name;
    private String email;
    private UserRole role;

    public AuthResponse() {
    }

    public AuthResponse(Integer id, String name, String email, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }
}