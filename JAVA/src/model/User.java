package model;

import java.util.Optional;

public class User implements Displayable {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String role;
    private String phone;

    public User(int userId, String username, String email, String password, String role, String phone) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format. Must contain '@'.");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long.");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone; // optional
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    protected String getPassword() {
        return password;
    }
    
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void updatePassword(String oldPassword, String newPassword) {
        if (checkPassword(oldPassword)) {
            if (newPassword != null && newPassword.length() >= 6) {
                this.password = newPassword;
            } else {
                throw new IllegalArgumentException("New password must be at least 6 characters.");
            }
        } else {
            throw new SecurityException("Old password does not match.");
        }
    }

    public String getRole() {
        return role;
    }

    public Optional<String> getPhone() {
        return Optional.ofNullable(phone);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void displayUserInfo() {
        System.out.println("User ID: " + userId + " | Username: " + username + " | Email: " + email + " | Role: " + role);
    }

    @Override
    public void displayBasicInfo() {
        System.out.println("[Displayable Info] User: " + username + " registered as " + role);
    }
}
