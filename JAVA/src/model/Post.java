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

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public PostType getPostType() {
        return postType;
    }
}
