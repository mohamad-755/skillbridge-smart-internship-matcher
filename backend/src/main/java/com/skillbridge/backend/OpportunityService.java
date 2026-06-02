package com.skillbridge.backend;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OpportunityService {

    private List<Opportunity> opportunities = new ArrayList<>();

    public OpportunityService() {
        opportunities.add(new Opportunity(
                1,
                "Backend Internship",
                "TechStart",
                "Internship",
                "Beirut",
                "2026-07-15",
                "A beginner-friendly backend internship for CS students.",
                Arrays.asList("Java", "Spring Boot", "Git")));

        opportunities.add(new Opportunity(
                2,
                "AI Bootcamp",
                "DataLab",
                "Bootcamp",
                "Remote",
                "2026-08-01",
                "A practical AI bootcamp for students interested in machine learning.",
                Arrays.asList("Python", "Machine Learning", "Git")));

        opportunities.add(new Opportunity(
                3,
                "Frontend Volunteer Program",
                "CodeForGood",
                "Volunteering",
                "Beirut",
                "2026-06-20",
                "Volunteer with a tech NGO and help build simple websites.",
                Arrays.asList("HTML", "CSS", "JavaScript", "React")));
    }

    public List<Opportunity> getAllOpportunities() {
        return opportunities;
    }

    public Opportunity getOpportunityById(int id) {
        for (Opportunity opportunity : opportunities) {
            if (opportunity.getId() == id) {
                return opportunity;
            }
        }

        return null;
    }

    public Opportunity addOpportunity(Opportunity opportunity) {
        opportunities.add(opportunity);
        return opportunity;
    }

    public List<Opportunity> searchOpportunities(String keyword) {
        List<Opportunity> results = new ArrayList<>();

        String lowerKeyword = keyword.toLowerCase();

        for (Opportunity opportunity : opportunities) {
            boolean foundInBasicFields = opportunity.getTitle().toLowerCase().contains(lowerKeyword) ||
                    opportunity.getOrganization().toLowerCase().contains(lowerKeyword) ||
                    opportunity.getCategory().toLowerCase().contains(lowerKeyword) ||
                    opportunity.getLocation().toLowerCase().contains(lowerKeyword) ||
                    opportunity.getDescription().toLowerCase().contains(lowerKeyword);

            boolean foundInSkills = false;

            for (String skill : opportunity.getRequiredSkills()) {
                if (skill.equalsIgnoreCase(keyword)) {
                    foundInSkills = true;
                    break;
                }
            }

            if (foundInBasicFields || foundInSkills) {
                results.add(opportunity);
            }
        }

        return results;
    }

    public List<Opportunity> filterOpportunities(String category, String location) {
        List<Opportunity> results = new ArrayList<>();

        for (Opportunity opportunity : opportunities) {
            boolean categoryMatches = true;
            boolean locationMatches = true;

            if (category != null && !category.isEmpty()) {
                categoryMatches = opportunity.getCategory().equalsIgnoreCase(category);
            }

            if (location != null && !location.isEmpty()) {
                locationMatches = opportunity.getLocation().equalsIgnoreCase(location);
            }

            if (categoryMatches && locationMatches) {
                results.add(opportunity);
            }
        }

        return results;
    }

    public List<Opportunity> sortOpportunities(String by) {
        List<Opportunity> sortedOpportunities = new ArrayList<>(opportunities);

        if (by.equalsIgnoreCase("deadline")) {
            sortedOpportunities.sort(Comparator.comparing(Opportunity::getDeadline));
        } else if (by.equalsIgnoreCase("title")) {
            sortedOpportunities.sort(Comparator.comparing(Opportunity::getTitle));
        } else if (by.equalsIgnoreCase("category")) {
            sortedOpportunities.sort(Comparator.comparing(Opportunity::getCategory));
        } else {
            return sortedOpportunities;
        }

        return sortedOpportunities;
    }
}