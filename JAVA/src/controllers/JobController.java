package controllers;

import model.Job;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobController {
    // HashMap for fast O(1) job lookups matching jobId to the Job object
    private Map<Integer, Job> jobs;

    public JobController() {
        this.jobs = new HashMap<>();
    }

    public void addJob(Job job) {
        if (job != null) {
            jobs.put(job.getJobId(), job);
        }
    }

    public List<Job> getAllJobs() {
        return new ArrayList<>(jobs.values());
    }

    public void removeJob(Job job) {
        if (job != null) {
            jobs.remove(job.getJobId());
        }
    }

    public Job getJobById(int jobId) {
        return jobs.get(jobId);
    }

    // Search jobs by keyword in title using simple loop
    public List<Job> searchJobsByTitle(String keyword) {
        List<Job> results = new ArrayList<>();
        for (Job job : jobs.values()) {
            if (job.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(job);
            }
        }
        return results;
    }

    // Filter jobs by location
    public List<Job> searchJobsByLocation(String location) {
        List<Job> results = new ArrayList<>();
        for (Job job : jobs.values()) {
            if (job.getLocation().equalsIgnoreCase(location)) {
                results.add(job);
            }
        }
        return results;
    }

    // Minimum salary filter 
    public List<Job> filterByMinimumSalary(double minSalary) {
        List<Job> results = new ArrayList<>();
        for (Job job : jobs.values()) {
            if (job.getSalary() >= minSalary) {
                results.add(job);
            }
        }
        return results;
    }
}
