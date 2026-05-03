package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Profile {
    private int profileId;
    private int userId;
    private String fullName;
    private String headline;
    private String summary;
    private String profilePhoto; // can be null
    private List<String> experience;
    private List<String> skills;

    public Profile(int profileId, int userId, String fullName, String headline, String profilePhoto) {
        this.profileId = profileId;
        this.userId = userId;
        this.fullName = fullName;
        this.headline = headline;
        this.profilePhoto = profilePhoto;
        this.experience = new ArrayList<>();
        this.skills = new ArrayList<>();
    }

    public int getProfileId() {
        return profileId;
    }
    public int getUserId() {
        return userId;
    }
    public String getFullName() {
        return fullName;
    }
    public String getHeadline() {
        return headline;
    }
    
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void addExperience(String jobTitle, String company, String duration) {
        String entry = jobTitle + " at " + company + " (" + duration + ")";
        this.experience.add(entry);
    }

    public List<String> getExperience() {
        return new ArrayList<>(experience);
    }

    public void addSkill(String skill) {
        if (!this.skills.contains(skill)) {
            this.skills.add(skill);
        }
    }

    public List<String> getSkills() {
        return new ArrayList<>(skills);
    }
    
    // Feature: Returns Optional to avoid null pointer exceptions
    public Optional<String> getProfilePhoto() {
        return Optional.ofNullable(profilePhoto);
    }
}
