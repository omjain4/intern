package model;

public interface Displayable {
    // Abstract method
    void displayBasicInfo();

    // 1. Java 8 Feature: Default Method in Interface
    default void displayWelcomeMessage() {
        System.out.println("- Welcome to the LinkedIn Clone Display System! -");
    }

    // 2. Java 8 Feature: Static Method in Interface
    static String getSystemVersion() {
        return "v1.0 (Java 8 Edition)";
    }
}
