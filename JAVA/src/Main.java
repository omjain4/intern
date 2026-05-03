import model.Company;
import model.Displayable;
import model.Job;
import model.Post;
import model.User;
import model.NetworkAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== LinkedIn Clone Core Features (Java 8 Enhanced) ===\n");
        // Java 8 Feature: Calling a Static Method from Interface
        System.out.println("System Version: " + Displayable.getSystemVersion() + "\n");

        // 1. Creating Users
        System.out.println("--- 1. Users ---");
        User user1 = new User(1, "alice@example.com", "developer", "555-1234");
        User user2 = new User(2, "bob@example.com", "recruiter", null); // Bob has no phone number
        List<User> users = Arrays.asList(user1, user2); // Using Arrays.asList for Java 8
        
        // Java 8 Feature: Method References (iterating and calling instance methods)
        users.forEach(User::displayUserInfo);
        
        System.out.println("\n--- Java 8 Default Methods & Optional ---");
        // Java 8 Feature: Default Interface Methods & Optional
        for (User user : users) {
             user.displayWelcomeMessage(); // from Displayable
             user.displayBasicInfo(); // implemented abstract
             
             // Java 8 Feature: Optional usage with if-else and ifPresent lambda
             if (user.getPhone().isPresent()) {
                 user.getPhone().ifPresent(phone -> System.out.println("Phone number is present: " + phone));
             } else {
                 System.out.println(user.getEmail() + " does not have a phone number registered.");
             }
        }
        System.out.println();

        // 2. Creating Companies
        System.out.println("--- 2. Companies ---");
        Company company1 = new Company(101, "TechCorp Solutions", "Software", "New York");
        Company company2 = new Company(102, "DataSmart Inc.", "Data Analytics", "San Francisco");

        company1.printCompanyDetails();
        company2.printCompanyDetails();
        System.out.println();

        // 3. Creating Jobs
        System.out.println("--- 3. Job Postings ---");
        List<Job> jobList = new ArrayList<>();
        // Java 8 Feature: java.time API (LocalDateTime)
        jobList.add(new Job(1, "Java SpringBoot Developer", "New York", 110000, LocalDateTime.now()));
        jobList.add(new Job(2, "Frontend React Engineer", "Remote", 95000, LocalDateTime.now()));
        jobList.add(new Job(3, "Junior HTML Analyst", "New York", 50000, LocalDateTime.now().minusDays(2)));

        // Java 8 Feature: Streams & Filters
        System.out.println("\n--- Java 8 Streams (Filter NYC jobs paying > 100k) ---");
        List<Job> filteredJobs = jobList.stream()
            .filter(job -> job.getLocation().equals("New York"))
            .filter(job -> job.getSalary() > 100000)
            .collect(Collectors.toList());

        filteredJobs.forEach(job -> {
            System.out.println("Premium NYC Job: " + job.getTitle() + " ($" + job.getSalary() + ")");
        });
        System.out.println();

        // 4. Using Custom Functional Interfaces & Lambdas 
        System.out.println("--- 4. Functional Interfaces & Custom Lambdas ---");
        // Java 8 Feature: Custom Functional Interface (@FunctionalInterface) & Lambda implementation
        NetworkAction sendConnectionRequest = u -> System.out.println("Sending a connection request to: " + u.getEmail());
        
        // Execute the lambda action
        sendConnectionRequest.performAction(user1);
        sendConnectionRequest.performAction(user2);

        // 5. Creating Posts & Reacting to them
        System.out.println("\n--- 5. Posts & Reactions ---");
        Post post1 = new Post(1, user1.getUserId(), "Just learned some awesome Java basic features!");
        post1.addLike();
        post1.displayPost();
    }
}