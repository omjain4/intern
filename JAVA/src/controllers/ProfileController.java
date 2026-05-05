package controllers;

import model.Profile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class ProfileController {
    private Map<Integer, Profile> profiles;

    public ProfileController() {
        this.profiles = new HashMap<>();
    }

    public void addProfile(Profile profile) {
        if (profile != null) {
            profiles.put(profile.getUserId(), profile);
            System.out.println("Profile added to registry.");
        }
    }

    public List<Profile> getAllProfiles() {
        // Returning as an ArrayList from the Map's values collection
        return new ArrayList<>(profiles.values());
    }

    public void removeProfile(int userId) {
        if (profiles.containsKey(userId)) {
            profiles.remove(userId);
            System.out.println("Profile removed.");
        }
    }

   //getting profile using hashmap
    public Profile getProfileByUserId(int userId) {
        return profiles.get(userId);
    }

    //Ensures the headline isn't too long
public void setProfileHeadline(int userId, String newHeadline) {
    Profile profile = getProfileByUserId(userId); //
    
    if (profile == null) {
        System.out.println("Error: Profile not found.");
        return;
    }

    //Limit headline to 100 characters
    if (newHeadline != null && newHeadline.length() <= 100) {
        profile.setHeadline(newHeadline);
        System.out.println("Headline updated.");
    } else {
        System.out.println("Error: Headline is too long (Max 100 chars).");
    }
}

//Ensure the summary is minimum 10 words
public void setProfileSummary(int userId, String newSummary) {
    Profile profile = getProfileByUserId(userId); //

    if (profile != null) {
        if (newSummary != null && newSummary.trim().split("\\s+").length >= 10) {
            profile.setSummary(newSummary);
            System.out.println("Summary updated.");
        } else {
            System.out.println("Error: Summary must be at least 10 words long.");
        }
    }
}  


    //Add an experience to a user's profile
    public void addExperienceToProfile(int userId, String jobTitle, String company, String duration) {
        Profile profile = getProfileByUserId(userId);
        if (profile != null) {
            String entry = jobTitle + " at " + company + " (" + duration + ")";
            profile.getExperience().add(entry);
            System.out.println("Experience added.");
        }
    }

    // LinkedIn-like logic: Add a skill to a user's profile
    public void addSkillToProfile(int userId, String skill) {
        Profile profile = getProfileByUserId(userId);
        if (profile != null) {
            if (!profile.getSkills().contains(skill)) {
                profile.getSkills().add(skill);
                System.out.println("Skill added.");
            } else {
                System.out.println("Skill already exists.");
            }
        }
    }
    
    // Find people with a specific skill
    public List<Profile> searchProfilesBySkill(String skill) {
        List<Profile> results = new ArrayList<>();
        for (Profile profile : profiles.values()) {
            for (String profileSkill : profile.getSkills()) {
                if (profileSkill.equalsIgnoreCase(skill)) {
                    results.add(profile);
                    break; // Move to the next profile
                }
            }
        }
        return results;
    }
}
