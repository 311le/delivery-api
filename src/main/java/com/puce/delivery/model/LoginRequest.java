package com.puce.delivery.model;

public class LoginRequest {
    private String name;
    private String passwordHash;

    // Constructor
    public LoginRequest(String name, String passwordHash) {
        this.name = name;
        this.passwordHash = passwordHash;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
