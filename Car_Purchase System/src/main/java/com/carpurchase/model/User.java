package com.carpurchase.model;

import java.time.LocalDateTime;

/**
 * Represents a user in the system.
 */
public class User {
    private String id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String role;
    private boolean active;
    private String createdDate;
    
    /**
     * Creates a new user with default values.
     * Sets a unique ID, active status, and current timestamp.
     */
    public User() {
        this.id = "USER" + System.currentTimeMillis();
        this.active = true;
        this.createdDate = LocalDateTime.now().toString();
    }
    
    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public String getCreatedDate() { return createdDate; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }
    
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                '}';
    }
}