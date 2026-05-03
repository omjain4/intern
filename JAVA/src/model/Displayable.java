package model;

public interface Displayable {
    // Abstract method
    void displayBasicInfo();

    //Default Method in Interface
    default void displayWelcomeMessage() {
        System.out.println("- Welcome to the LinkedIn Display System! -");
    }
    
    // 2. Java 8 Feature: Static Method in Interface
    static String getSystemVersion() {
        return "(Java 8 Edition)";
    }
}
