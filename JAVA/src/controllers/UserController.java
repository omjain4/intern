package controllers;

import model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    // Storing users by their email (unique identifier) using a HashMap
    private Map<String, User> userMapByEmail;

    public UserController() {
        this.userMapByEmail = new HashMap<>();
    }

    // Register a new user, checking if username or email already exists
    public boolean registerUser(User user) {
        if (user == null) {
            System.out.println("Error: User cannot be null.");
            return false;
        }

        // Fast lookup via Map containsKey
        if (userMapByEmail.containsKey(user.getEmail().toLowerCase())) {
            System.out.println("Error: Email already in use.");
            return false;
        }

        // Beginner friendly loop over map values to check username
        for (User existingUser : userMapByEmail.values()) {
            if (existingUser.getUsername().equalsIgnoreCase(user.getUsername())) {
                System.out.println("Error: Username already taken.");
                return false;
            }
        }
        
        userMapByEmail.put(user.getEmail().toLowerCase(), user);
        System.out.println("User registered successfully!");
        return true;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMapByEmail.values());
    }

    public void removeUser(User user) {
        if (user != null) {
            userMapByEmail.remove(user.getEmail().toLowerCase());
        }
    }

    // Authentication: Login logic checks matching email and password
    public User loginUser(String email, String password) {
        // Fast lookup since we map by email
        User user = userMapByEmail.get(email.toLowerCase());
        if (user != null && user.checkPassword(password)) {
            System.out.println("Login successful!");
            return user;
        }
        System.out.println("Error: Invalid email or password.");
        return null;
    }

    public User findUserByEmail(String email) {
        return userMapByEmail.get(email.toLowerCase());
    }

    public User findUserByUsername(String username) {
        for (User user : userMapByEmail.values()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    // Filter users by Role using basic loops over Map.values()
    public List<User> findUsersByRole(String role) {
        List<User> result = new ArrayList<>();
        for (User user : userMapByEmail.values()) {
            if (user.getRole().equalsIgnoreCase(role)) {
                result.add(user);
            }
        }
        return result;
    }
}
