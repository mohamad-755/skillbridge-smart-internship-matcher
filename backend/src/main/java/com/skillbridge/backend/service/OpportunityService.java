package com.skillbridge.backend.service;

import com.skillbridge.backend.model.Opportunity;
import com.skillbridge.backend.repository.OpportunityRepository;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;

    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }

    public Opportunity getOpportunityById(int id) {
        return opportunityRepository.findById(id).orElse(null);
    }

    public Opportunity addOpportunity(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    public List<Opportunity> searchOpportunities(String keyword) {
        List<Opportunity> all = opportunityRepository.findAll();
        String lower = keyword.toLowerCase();
        return all.stream()
                .filter(o -> o.getTitle().toLowerCase().contains(lower) ||
                        o.getOrganization().toLowerCase().contains(lower) ||
                        o.getCategory().toLowerCase().contains(lower) ||
                        o.getLocation().toLowerCase().contains(lower) ||
                        o.getDescription().toLowerCase().contains(lower) ||
                        o.getRequiredSkills().stream().anyMatch(s -> s.equalsIgnoreCase(keyword)))
                .toList();
    }

    public List<Opportunity> filterOpportunities(String category, String location) {
        List<Opportunity> all = opportunityRepository.findAll();
        return all.stream()
                .filter(o -> (category == null || category.isEmpty() || o.getCategory().equalsIgnoreCase(category)))
                .filter(o -> (location == null || location.isEmpty() || o.getLocation().equalsIgnoreCase(location)))
                .toList();
    }

    public List<Opportunity> sortOpportunities(String by) {
        List<Opportunity> all = opportunityRepository.findAll();
        if (by.equalsIgnoreCase("deadline")) {
            all.sort(Comparator.comparing(Opportunity::getDeadline));
        } else if (by.equalsIgnoreCase("title")) {
            all.sort(Comparator.comparing(Opportunity::getTitle));
        } else if (by.equalsIgnoreCase("category")) {
            all.sort(Comparator.comparing(Opportunity::getCategory));
        }
        return all;
    }
}