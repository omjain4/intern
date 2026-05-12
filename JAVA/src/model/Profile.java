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

    public Profile(int profileId, int userId, String fullName, String headline) {
        this.profileId = profileId;
        this.userId = userId;
        this.fullName = fullName;
        this.headline = headline;
        this.profilePhoto = null;
        this.experience = new ArrayList<>();
        this.skills = new ArrayList<>();
    }

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

    public List<String> getExperience() {
        return experience;
    }

    // [BUG FIX - Missing Empty Array Validations]
    // The bug was: External systems could do `profile.setExperience(null)` causing `for(String experience : getExperience())` loops to crash.
    // Fix: Ensures arrays can never be internally null, falling back securely to empty structures automatically.
    public void setExperience(List<String> experience) {
        if (experience == null) {
            this.experience = new ArrayList<>();
        } else {
            this.experience = experience;
        }
    }

    public List<String> getSkills() {
        return skills;
    }

    // [BUG FIX - Missing Empty Array Validations] 
    // The bug was: Like experience arrays, skills could be overwritten via setter explicitly with null.
    // Fix: Forces array instantiation upon null detection.
    public void setSkills(List<String> skills) {
        if (skills == null) {
            this.skills = new ArrayList<>();
        } else {
            this.skills = skills;
        }
    }
    
    public String getProfilePhoto() {
        return profilePhoto;
    }
}
