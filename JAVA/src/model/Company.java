package model;

public class Company {
    private int companyId;
    private String name;
    private String industry;
    private String location;

    public Company(int companyId, String name, String industry, String location) {
        this.companyId = companyId;
        this.name = name;
        this.industry = industry;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }

    public String getLocation() {
        return location;
    }

    // A simple method to print company details
    public void printCompanyDetails() {
        System.out.println("Company Name: " + name);
        System.out.println("  Industry: " + industry);
        System.out.println("  Location: " + location);
    }
}
