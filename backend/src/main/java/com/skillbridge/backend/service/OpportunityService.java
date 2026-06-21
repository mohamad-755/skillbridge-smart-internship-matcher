package com.skillbridge.backend.service;

import com.skillbridge.backend.model.Opportunity;
import com.skillbridge.backend.repository.OpportunityRepository;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import com.skillbridge.backend.model.User;
import com.skillbridge.backend.model.UserRole;
import com.skillbridge.backend.repository.UserRepository;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final UserRepository userRepository;

    public OpportunityService(
            OpportunityRepository opportunityRepository,
            UserRepository userRepository) {
        this.opportunityRepository = opportunityRepository;
        this.userRepository = userRepository;
    }

    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }

    public Opportunity getOpportunityById(int id) {
        return opportunityRepository.findById(id).orElse(null);
    }

    public Opportunity addOpportunity(Integer userId, Opportunity opportunity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getRole() != UserRole.ADMIN) {
            throw new SecurityException("Only admins can create opportunities");
        }

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