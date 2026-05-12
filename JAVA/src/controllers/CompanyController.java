package controllers;

import model.Company;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyController {
    private Map<Integer, Company> companies;

    public CompanyController() {
        this.companies = new HashMap<>();
    }

    public void addCompany(Company company) {
        if (company != null) {
            // BUG - Silent Data Overwrite
            // The bug was: Adding duplicate Company ID erased the previous one automatically.
            // Fix: Check container to guard against duplicates before adding.
            if (companies.containsKey(company.getCompanyId())) {
                System.out.println("Error: Company with ID " + company.getCompanyId() + " already exists.");
                return;
            }
            companies.put(company.getCompanyId(), company);
        }
    }
    //creating a list of companies from the hashmap values as to not change the original map
    public List<Company> getAllCompanies() {
        return new ArrayList<>(companies.values());
    }

    public void removeCompany(int companyId) {
        companies.remove(companyId);
    }
    
    public Company getCompanyById(int companyId) {
        return companies.get(companyId);
    }

    //Search companies by industry
    public List<Company> getCompaniesByIndustry(String industry) {
        List<Company> results = new ArrayList<>();
        // BUG FIX - NullPointerException
        // The bug was: Null queries or existing Companies with missing industries crashed the loop mapping.
        // Fix: Verified both 'industry' query and 'company.getIndustry()' aren't null before calling `.equalsIgnoreCase()`.
        if (industry == null) return results;
        
        for (Company company : companies.values()) {
            if (company.getIndustry() != null && company.getIndustry().equalsIgnoreCase(industry)) {
                results.add(company);
            }
        }
        return results;
    }

    //Search companies by name keyword
    public List<Company> searchCompaniesByName(String keyword) {
        List<Company> results = new ArrayList<>();
        // BUG - NullPointerException
        // The bug was: Missing name keyword or null company names caused app crash.
        // Fix: Added explicit null filtering checks.
        if (keyword == null) return results;

        for (Company company : companies.values()) {
            if (company.getName() != null && company.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(company);
            }
        }
        return results;
    }

    public void printCompanyDetails(int companyId) {
        Company company = companies.get(companyId);
        if (company != null) {
            System.out.println("Company Name: " + company.getName());
            System.out.println("  Industry: " + company.getIndustry());
            System.out.println("  Location: " + company.getLocation());
        } else {
            System.out.println("Company not found.");
        }
    }
}
