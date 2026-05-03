package model;

import java.util.Optional;

// 3. Java 8 Feature: Functional Interface Annotation
@FunctionalInterface
public interface NetworkAction {
    // Only one abstract method allowed in functional interfaces
    void performAction(User user);

}