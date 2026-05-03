package controllers;

import model.Profile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class ProfileController {
    // Using HashMap for O(1) fast lookup by User ID
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

    // LinkedIn-like logic: Get Profile by User ID using HashMap lookup
    public Profile getProfileByUserId(int userId) {
        // Benefit of HashMap: No loop required to find by ID!
        return profiles.get(userId);
    }

    // LinkedIn-like logic: Update headline & summary
    public void updateProfileHeadlineAndSummary(int userId, String headline, String summary) {
        Profile profile = getProfileByUserId(userId);
        if (profile != null) {
            profile.setHeadline(headline);
            profile.setSummary(summary);
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Error: Profile not found.");
        }
    }

    // LinkedIn-like logic: Add an experience to a user's profile
    public void addExperienceToProfile(int userId, String jobTitle, String company, String duration) {
        Profile profile = getProfileByUserId(userId);
        if (profile != null) {
            profile.addExperience(jobTitle, company, duration);
            System.out.println("Experience added.");
        }
    }

    // LinkedIn-like logic: Add a skill to a user's profile
    public void addSkillToProfile(int userId, String skill) {
        Profile profile = getProfileByUserId(userId);
        if (profile != null) {
            profile.addSkill(skill);
            System.out.println("Skill added.");
        }
    }

    // Search connections / people by name
    public List<Profile> searchProfilesByName(String keyword) {
        List<Profile> results = new ArrayList<>();
        for (Profile profile : profiles.values()) {
            if (profile.getFullName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(profile);
            }
        }
        return results;
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
