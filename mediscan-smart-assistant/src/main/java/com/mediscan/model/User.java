package com.mediscan.model;

import java.util.UUID;

public class User {
    private final UUID userId;
    private String fullName;
    private String email;

    public User(UUID userId, String fullName, String email) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
    }

    public static User createNew(String fullName, String email) {
        return new User(UUID.randomUUID(), fullName, email);
    }

    public UUID getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
}
