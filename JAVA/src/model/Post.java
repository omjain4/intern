package model;

public class Post {
    public enum PostType {
        CELEBRATION, EVENT, NORMAL
    }

    private int postId;
    private int userId;
    private String content;
    private int likes; // reaction count
    private PostType postType;

    public Post(int postId, int userId, String content, PostType postType) {
        if (content != null && content.split("\\s+").length > 1000) {
            throw new IllegalArgumentException("Post content exceeds the maximum limit of 1000 words.");
        }
        
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.postType = postType;
        this.likes = 0;
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public PostType getPostType() {
        return postType;
    }

    // Method to add a like to the post
    public void addLike() {
        this.likes++;
    }

    // Method to display post-details
    public void displayPost() {
        System.out.println("User [" + userId + "] Posted a " + postType + ": " + content + " | Likes: " + likes);
    }
}
