package controllers;

import model.Post;
import model.Post.PostType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Post.PostType;
public class PostController {
    private Map<Integer, Post> posts;


    private PostType postType;
    public PostController() {
        this.posts = new HashMap<>();
    }

    //method to add a post
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
    //method to create a post with validation logic
    public Post createPost(int userId, String description, PostType postType) {
        if (userId <= 0) {
            System.out.println("Error: Invalid User ID.");
            return null;
        }

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

        //Generate a simple ID based on existing keys
        int newPostId = 1;
        if (!posts.isEmpty()) {
            newPostId = Collections.max(posts.keySet()) + 1;
        }

        Post newPost = new Post(newPostId, userId, description, postType);
        this.addPost(newPost);
        
        return newPost;
    }

    public List<Post> getAllPosts() {
        return new ArrayList<>(posts.values());
    }

    //Remove by Post ID and check if the requesting user is the owner to prevent unauthorized deletes
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

    //Like a post
    public void likePost(int postId) {
        Post post = posts.get(postId);
        if (post != null) {
             post.setLikes(post.getLikes() + 1);
             System.out.println("Post liked.");
        } else {
             System.out.println("Error: Post not found.");
        }
    }
  
    public void displayPost(int postId) {
        Post post = posts.get(postId);
        if (post != null) {
            System.out.println("User [" + post.getUserId() + "] Posted a " + post.getPostType() + ": " + post.getContent() + " | Likes: " + post.getLikes());
        }
    }
    //get post of certain user
    public List<Post> getPostsByUser(int userId) {
        List<Post> results = new ArrayList<>();
        for (Post post : posts.values()) {
            if (post.getUserId() == userId) {
                results.add(post);
            }
        }
        return results;
    }

    //Feed sorted by most likes
    public List<Post> getTrendingPosts() {
        List<Post> results = new ArrayList<>(posts.values());
        // Simple manual sort using Collections.sort and a basic comparator
        Collections.sort(results, (p1, p2) -> Integer.compare(p2.getLikes(), p1.getLikes()));
        return results;
    }
}
