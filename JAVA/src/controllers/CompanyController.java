package controllers;

import model.Company;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyController {
    // HashMap to map companyId to the Company object
    private Map<Integer, Company> companies;

    public CompanyController() {
        this.companies = new HashMap<>();
    }

    public void addCompany(Company company) {
        if (company != null) {
            companies.put(company.getCompanyId(), company);
        }
    }

    public List<Company> getAllCompanies() {
        return new ArrayList<>(companies.values());
    }

    public void removeCompany(int companyId) {
        companies.remove(companyId);
    }
    
    public Company getCompanyById(int companyId) {
        return companies.get(companyId);
    }

    // LinkedIn-like logic: Search companies by industry
    public List<Company> getCompaniesByIndustry(String industry) {
        List<Company> results = new ArrayList<>();
        for (Company company : companies.values()) {
            if (company.getIndustry().equalsIgnoreCase(industry)) {
                results.add(company);
            }
        }
        return results;
    }

    // LinkedIn-like logic: Search companies by name keyword
    public List<Company> searchCompaniesByName(String keyword) {
        List<Company> results = new ArrayList<>();
        for (Company company : companies.values()) {
            if (company.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(company);
            }
        }
        return results;
    }
}
