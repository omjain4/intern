package model;

import java.util.Optional;

public class Profile {
    private int profileId;
    private int userId;
    private String fullName;
    private String headline;
    private String profilePhoto; // can be null

    public Profile(int profileId, int userId, String fullName, String headline, String profilePhoto) {
        this.profileId = profileId;
        this.userId = userId;
        this.fullName = fullName;
        this.headline = headline;
        this.profilePhoto = profilePhoto;
    }

    public int getProfileId() { return profileId; }
    public int getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getHeadline() { return headline; }
    
    // Feature: Returns Optional to avoid null pointer exceptions
    public Optional<String> getProfilePhoto() {
        return Optional.ofNullable(profilePhoto);
    }
}
