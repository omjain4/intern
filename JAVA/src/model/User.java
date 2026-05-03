package model;

import java.util.Optional;

// Implementing an interface that has Java 8 default and static methods
public class User implements Displayable {
    private int userId;
    private String email;
    private String role;
    private String phone; // Added an optional field

    public User(int userId, String email, String role, String phone) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        this.phone = phone; // Can be null
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    // 4. Java 8 Feature: Optional
    public Optional<String> getPhone() {
        return Optional.ofNullable(phone);
    }

    // A simple method to display user info
    public void displayUserInfo() {
        System.out.println("User ID: " + userId + " | Email: " + email + " | Role: " + role);
    }

    @Override
    public void displayBasicInfo() {
        System.out.println("[Displayable Info] User: " + email + " registered as " + role);
    }
}
