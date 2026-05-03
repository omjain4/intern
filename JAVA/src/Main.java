import controllers.*;
import model.*;
import model.Post.PostType;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- LINKEDIN DEMO (WITH CONTROLLERS) ---\n");

        // 1. Initialize Controllers
        UserController userController = new UserController();
        ProfileController profileController = new ProfileController();
        JobController jobController = new JobController();
        CompanyController companyController = new CompanyController();
        PostController postController = new PostController();

        // 2. Users: Create and Register via Controller
        System.out.println(">>> 1. USERS & PROFILES");
        User alice = new User(1, "AliceDev", "alice@example.com", "pass123", "user", "555-1234");
        User bob = new User(2, "BobRecruiter", "bob@example.com", "secure456", "recruiter", null);
        
        userController.registerUser(alice);
        userController.registerUser(bob);

        // Test login
        User loggedInUser = userController.loginUser("alice@example.com", "pass123");
        if (loggedInUser != null) {
            System.out.println("Alice is logged in!\n");
        }

        // Create profiles for them
        Profile aliceProfile = new Profile(1, alice.getUserId(), "Alice Smith", "Software Engineer", null);
        Profile bobProfile = new Profile(2, bob.getUserId(), "Bob Johnson", "Tech Recruiter at FutureJobs", null);
        profileController.addProfile(aliceProfile);
        profileController.addProfile(bobProfile);

        // Update profiles using controller logic
        profileController.updateProfileHeadlineAndSummary(alice.getUserId(), "Senior Java Developer", "Passionate about building scalable systems.");
        profileController.addExperienceToProfile(alice.getUserId(), "Java Backend Dev", "TechCorp", "2020-2025");
        profileController.addSkillToProfile(alice.getUserId(), "Java");
        profileController.addSkillToProfile(alice.getUserId(), "Spring Boot");

        System.out.println("\n>>> 2. COMPANIES & JOBS");
        // Add companies
        Company comp1 = new Company(1, "TechCorp", "IT", "New York");
        companyController.addCompany(comp1);

        // Add jobs via JobController
        Job job1 = new Job(1, "Senior Java Developer", "New York", 120000, java.time.LocalDateTime.now());
        Job job2 = new Job(2, "Junior QA Engineer", "Remote", 60000, java.time.LocalDateTime.now());
        jobController.addJob(job1);
        jobController.addJob(job2);

        // View premium jobs
        System.out.println("Filtering Jobs > $100k:");
        for (Job j : jobController.filterByMinimumSalary(100000)) {
            System.out.println(" - " + j.getTitle() + " paying " + j.getSalary());
        }

        System.out.println("\n>>> 3. POSTS & FEED");
        // Create posts via PostController
        Post p1 = postController.createPost(alice.getUserId(), "I'm thrilled to announce I just mastered Java Controllers!", PostType.CELEBRATION);
        Post p2 = postController.createPost(bob.getUserId(), "TechCorp is hiring for Java roles! Check my page.", PostType.NORMAL);
        
        // Simulating likes
        if (p1 != null) postController.likePost(p1.getPostId());
        if (p1 != null) postController.likePost(p1.getPostId()); // 2 likes

        System.out.println("\nChecking Alice's Activity:");
        for (Post p : postController.getPostsByUser(alice.getUserId())) {
            p.displayPost();
        }

        System.out.println("\nChecking the Trending Feed:");
        for (Post p : postController.getTrendingPosts()) {
            p.displayPost();
        }
    }
}