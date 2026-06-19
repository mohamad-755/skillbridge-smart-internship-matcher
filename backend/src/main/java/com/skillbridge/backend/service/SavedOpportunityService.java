package com.skillbridge.backend.service;

import com.skillbridge.backend.model.Opportunity;
import com.skillbridge.backend.model.SavedOpportunity;
import com.skillbridge.backend.model.User;
import com.skillbridge.backend.repository.OpportunityRepository;
import com.skillbridge.backend.repository.SavedOpportunityRepository;
import com.skillbridge.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skillbridge.backend.dto.SavedOpportunityResponse;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class SavedOpportunityService {

    private final SavedOpportunityRepository savedOpportunityRepository;
    private final UserRepository userRepository;
    private final OpportunityRepository opportunityRepository;

    public SavedOpportunityService(
            SavedOpportunityRepository savedOpportunityRepository,
            UserRepository userRepository,
            OpportunityRepository opportunityRepository) {
        this.savedOpportunityRepository = savedOpportunityRepository;
        this.userRepository = userRepository;
        this.opportunityRepository = opportunityRepository;
    }

    public SavedOpportunityResponse saveOpportunity(Integer userId, Integer opportunityId) {
        if (savedOpportunityRepository.existsByUserIdAndOpportunityId(userId, opportunityId)) {
            throw new IllegalArgumentException("Opportunity is already saved");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Opportunity opportunity = opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new IllegalArgumentException("Opportunity not found"));

        SavedOpportunity savedOpportunity = new SavedOpportunity(null, user, opportunity);

        return toResponse(savedOpportunityRepository.save(savedOpportunity));
    }

    public List<SavedOpportunityResponse> getSavedOpportunities(Integer userId) {
        return savedOpportunityRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void unsaveOpportunity(Integer userId, Integer opportunityId) {
        if (!savedOpportunityRepository.existsByUserIdAndOpportunityId(userId, opportunityId)) {
            throw new IllegalArgumentException("Saved opportunity not found");
        }

        savedOpportunityRepository.deleteByUserIdAndOpportunityId(userId, opportunityId);
    }

    private SavedOpportunityResponse toResponse(SavedOpportunity savedOpportunity) {
        Opportunity opportunity = savedOpportunity.getOpportunity();

        return new SavedOpportunityResponse(
                savedOpportunity.getId(),
                opportunity.getId(),
                opportunity.getTitle(),
                opportunity.getOrganization(),
                opportunity.getCategory(),
                opportunity.getLocation(),
                opportunity.getDeadline(),
                opportunity.getDescription());
    }
}