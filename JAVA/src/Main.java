import controllers.*;
import model.*;
import model.Post.PostType;

public class Main {
    public static void main(String[] args) {

        // 1. Initialize Controllers
        UserController userController = new UserController();
        ProfileController profileController = new ProfileController();
        JobController jobController = new JobController();
        CompanyController companyController = new CompanyController();
        PostController postController = new PostController();

        // 2. Users: Create and Register via Controller
        System.out.println("1. USERS & PROFILES");
        User alice = new User(1, "AliceDev", "alice@example.com", "pass123", "user", "555-1234");
        // Simplified object creation: no need to provide standard omitted parameters
        User bob = new User(2, "BobRecruiter", "bob@example.com", "secure456", "recruiter");
        
        userController.registerUser(alice);
        userController.registerUser(bob);

        // Test login
        User loggedInUser = userController.loginUser("alice@example.com", "pass123");
        if (loggedInUser != null) {
            System.out.println("Alice is logged in!\n");
        }

        // Create profiles for them using simplified constructors (defaults to null photo)
        Profile aliceProfile = new Profile(1, alice.getUserId(), "Alice Smith", "Software Engineer");
        Profile bobProfile = new Profile(2, bob.getUserId(), "Bob Johnson", "Tech Recruiter at FutureJobs");
        profileController.addProfile(aliceProfile);
        profileController.addProfile(bobProfile);

        // Update profiles using controller logic

        profileController.addExperienceToProfile(alice.getUserId(), "Java Backend Dev", "TechCorp", "2020-2025");
        profileController.addSkillToProfile(alice.getUserId(), "Java");
        profileController.addSkillToProfile(alice.getUserId(), "Spring Boot");

        System.out.println("\2. COMPANIES & JOBS");
        // Add companies
        Company comp1 = new Company(1, "TechCorp", "IT", "New York");
        companyController.addCompany(comp1);

        // Add jobs via JobController
        Job job1 = new Job(1, "Java Developer", "New York", 120000, java.time.LocalDateTime.now());
        Job job2 = new Job(2, "Junior Developer", "Remote", 60000, java.time.LocalDateTime.now());
        jobController.addJob(job1);
        jobController.addJob(job2);

        // View premium jobs
        System.out.println("Filtering Jobs > $100k:");
        for (Job j : jobController.filterByMinimumSalary(100000)) {
            System.out.println(" - " + j.getTitle() + " paying " + j.getSalary());
        }

        System.out.println("\n3. POSTS & FEED");
        // Create posts via PostController
        Post p1 = postController.createPost(alice.getUserId(), "I'm thrilled to announce I just mastered Java Controllers!", PostType.CELEBRATION);
        // Using simplified createPost method where PostType defaults to NORMAL automatically
        Post p2 = postController.createPost(bob.getUserId(), "TechCorp is hiring for Java roles! Check my page.");
        Post P3 = postController.createPost(bob.getUserId(), "hello this is test post");
        // Simulating likes
        if (p1 != null) postController.likePost(p1.getPostId());
        if (p1 != null) postController.likePost(p1.getPostId()); // 2 likes

        System.out.println("\nChecking Alice's Activity:");
        for (Post p : postController.getPostsByUser(alice.getUserId())) {
            postController.displayPost(p.getPostId());
        }

        System.out.println("\nChecking the Trending Feed:");
        for (Post p : postController.getTrendingPosts()) {
            postController.displayPost(p.getPostId());
        }
        companyController.printCompanyDetails(1);
    }
}