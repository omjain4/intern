package controllers;

import model.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    // Primary index
    private Map<String, User> userMapByEmail;
    // Secondary index (NEW)
    private Map<String, User> userMapByUsername;

    public UserController() {
        this.userMapByEmail = new HashMap<>();
        this.userMapByUsername = new HashMap<>(); // Initialize new map
    }

    public boolean registerUser(User user) {
        if (user == null) {
            System.out.println("Error: User cannot be null.");
            return false;
        }
        
        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            System.out.println("Error: Invalid email format. Must contain '@'.");
            return false;
        }
        //check for duplicate Email
        if (userMapByEmail.containsKey(user.getEmail().toLowerCase())) {
            System.out.println("Error: Email already in use.");
            return false;
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            System.out.println("Error: Password must be at least 6 characters long.");
            return false;
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            System.out.println("Error: Username cannot be empty.");
            return false;
        }
        //check for duplicate Username
        if (userMapByUsername.containsKey(user.getUsername().toLowerCase())) {
            System.out.println("Error: Username already taken.");
            return false;
        }
        
        // 3. Save the user to BOTH maps to keep them in sync
        userMapByEmail.put(user.getEmail().toLowerCase(), user);
        userMapByUsername.put(user.getUsername().toLowerCase(), user);
        
        System.out.println("User registered successfully!");
        return true;
    }

    // find user with username
    public User findUserByUsername(String username) {
        return userMapByUsername.get(username.toLowerCase()); //0(1)
    }

    public void removeUser(User user) {
        if (user != null) {
            userMapByEmail.remove(user.getEmail().toLowerCase());
            userMapByUsername.remove(user.getUsername().toLowerCase());
        }
    }
    

    //Login logic checks matching email and password
    public User loginUser(String email, String password) {
        User user = userMapByEmail.get(email.toLowerCase());
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            return user;
        }
        System.out.println("Error: Invalid email or password.");
        return null;
    }

    public void updatePassword(String email, String oldPassword, String newPassword) {
        User user = userMapByEmail.get(email.toLowerCase());
        if (user != null) {
            if (user.getPassword().equals(oldPassword)) {
                if (newPassword != null && newPassword.length() >= 6) {
                    user.setPassword(newPassword);
                    System.out.println("Password updated successfully.");
                } else {
                    System.out.println("Error: New password must be at least 6 characters.");
                }
            } else {
                System.out.println("Error: Old password does not match.");
            }
        } else {
            System.out.println("Error: User not found.");
        }
    }

    public User findUserByEmail(String email) {
        return userMapByEmail.get(email.toLowerCase()); //0(1)
    }
}
