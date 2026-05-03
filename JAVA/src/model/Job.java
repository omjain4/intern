package model;

import java.time.LocalDateTime;
import java.util.Optional;

public class Job {
    private int jobId;
    private String title;
    private String location;
    private double salary;
    private LocalDateTime postedDate;

    public Job(int jobId, String title, String location, double salary, LocalDateTime postedDate) {
        this.jobId = jobId;
        this.title = title;
        this.location = location;
        this.salary = salary;
        this.postedDate = postedDate;
    }

    public int getJobId() { return jobId; }
    public String getTitle() { return title; }
    public String getLocation() { return location; }
    public double getSalary() { return salary; }
    public LocalDateTime getPostedDate() { return postedDate; }

    @Override
    public String toString() {
        return "Job{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", salary=" + salary +
                '}';
    }
}
