package model;

import java.util.Optional;

public class User implements Displayable {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String role;
    private String phone;

    public User(int userId, String username, String email, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = null;
    }

    public User(int userId, String username, String email, String password, String role, String phone) {
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

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
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
