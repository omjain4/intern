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

    public int getCompanyId() {
        return companyId;
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
}
