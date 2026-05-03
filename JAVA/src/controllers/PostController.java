package controllers;

import model.Post;
import model.Post.PostType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostController {
    // HashMap indexing Posts by their postId
    private Map<Integer, Post> posts;

    public PostController() {
        this.posts = new HashMap<>();
    }

    // Added logic: Prevent null posts and duplicate post IDs
    public void addPost(Post post) {
        if (post == null) {
            System.out.println("Error: Post cannot be null.");
            return;
        }
        
        if (posts.containsKey(post.getPostId())) {
             System.out.println("Error: Post with ID " + post.getPostId() + " already exists.");
             return;
        }
        
        posts.put(post.getPostId(), post);
    }

    // A beginner-friendly method to create and add a new post directly
    public Post createPost(int userId, String description, PostType postType) {
        // 1. Basic validation
        if (userId <= 0) {
            System.out.println("Error: Invalid User ID.");
            return null;
        }

        // Count words using basic split
        int wordCount = 0;
        if (description != null && !description.trim().isEmpty()) {
            wordCount = description.trim().split("\\s+").length;
        }

        if (wordCount > 1000) {
            System.out.println("Error: Description exceeds 1000 words limit.");
            return null;
        }

        if (postType == PostType.NORMAL && wordCount == 0) {
            System.out.println("Error: Regular posts must have a description.");
            return null;
        }

        // 2. Generate a simple ID based on existing keys
        int newPostId = 1;
        if (!posts.isEmpty()) {
            newPostId = Collections.max(posts.keySet()) + 1;
        }

        // 3. Create and add object
        Post newPost = new Post(newPostId, userId, description, postType);
        this.addPost(newPost);
        
        return newPost;
    }

    // Added logic: Return a copy of the list to protect the internal state from external modifications
    public List<Post> getAllPosts() {
        return new ArrayList<>(posts.values());
    }

    // Added logic: Remove by Post ID and check if the requesting user is the owner to prevent unauthorized deletes
    public void removePost(int postId, int requestingUserId) {
        Post postToRemove = posts.get(postId);
        
        if (postToRemove == null) {
             System.out.println("Error: Post with ID " + postId + " not found.");
             return;
        }

        if (postToRemove.getUserId() != requestingUserId) {
             System.out.println("Error: User " + requestingUserId + " is not authorized to delete this post.");
             return;
        }

        posts.remove(postId);
        System.out.println("Post successfully deleted.");
    }

    // LinkedIn-like logic: Like a post
    public void likePost(int postId) {
        Post post = posts.get(postId);
        if (post != null) {
             post.addLike();
        }
    }

    // LinkedIn-like logic: Get all posts by a specific user showing in their activity
    public List<Post> getPostsByUser(int userId) {
        List<Post> results = new ArrayList<>();
        for (Post post : posts.values()) {
            if (post.getUserId() == userId) {
                results.add(post);
            }
        }
        return results;
    }

    // LinkedIn-like logic: Feed sorted by most likes (Trending posts)
    public List<Post> getTrendingPosts() {
        List<Post> results = new ArrayList<>(posts.values());
        // Simple manual sort using Collections.sort and a basic comparator
        Collections.sort(results, (p1, p2) -> Integer.compare(p2.getLikes(), p1.getLikes()));
        return results;
    }

    // LinkedIn-like logic: Filter feed by post type (e.g., only CELEBRATION or EVENT)
    public List<Post> getPostsByType(PostType type) {
        List<Post> results = new ArrayList<>();
        for (Post post : posts.values()) {
            if (post.getPostType() == type) {
                 results.add(post);
            }
        }
        return results;
    }
}
