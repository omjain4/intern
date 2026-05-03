package model;

public class Post {
    private int postId;
    private int userId;
    private String content;
    private int likes; // reaction count

    public Post(int postId, int userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.likes = 0; // Starts with 0 likes
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

    // Method to add a like to the post
    public void addLike() {
        this.likes++;
    }

    // Method to display post details
    public void displayPost() {
        System.out.println("User [" + userId + "] Posted: " + content + " | Likes: " + likes);
    }
}
