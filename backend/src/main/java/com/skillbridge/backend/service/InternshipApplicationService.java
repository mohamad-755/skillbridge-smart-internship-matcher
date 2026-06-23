package com.skillbridge.backend.service;

import com.skillbridge.backend.dto.ApplicationResponse;
import com.skillbridge.backend.model.ApplicationStatus;
import com.skillbridge.backend.model.InternshipApplication;
import com.skillbridge.backend.model.Opportunity;
import com.skillbridge.backend.model.User;
import com.skillbridge.backend.model.UserRole;
import com.skillbridge.backend.repository.InternshipApplicationRepository;
import com.skillbridge.backend.repository.OpportunityRepository;
import com.skillbridge.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InternshipApplicationService {

    private final InternshipApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final OpportunityRepository opportunityRepository;

    public InternshipApplicationService(
            InternshipApplicationRepository applicationRepository,
            UserRepository userRepository,
            OpportunityRepository opportunityRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.opportunityRepository = opportunityRepository;
    }

    public ApplicationResponse createApplication(Integer userId, Integer opportunityId) {
        if (applicationRepository.existsByUserIdAndOpportunityId(userId, opportunityId)) {
            throw new IllegalArgumentException("Application already exists for this opportunity");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getRole() != UserRole.STUDENT) {
            throw new SecurityException("Only students can apply to opportunities");
        }

        Opportunity opportunity = opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new IllegalArgumentException("Opportunity not found"));

        InternshipApplication application = new InternshipApplication(
                null,
                user,
                opportunity,
                ApplicationStatus.APPLIED);

        return toResponse(applicationRepository.save(application));
    }

    public List<ApplicationResponse> getApplicationsForUser(Integer userId) {
        return applicationRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ApplicationResponse updateStatus(Integer userId, Integer applicationId, ApplicationStatus status) {
        InternshipApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        if (!application.getUser().getId().equals(userId)) {
            throw new SecurityException("You can only update your own applications");
        }
        application.setStatus(status);

        return toResponse(applicationRepository.save(application));
    }

    public void deleteApplication(Integer userId, Integer applicationId) {
        InternshipApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));

        if (!application.getUser().getId().equals(userId)) {
            throw new SecurityException("You can only delete your own applications");
        }

        applicationRepository.delete(application);
    }

    private ApplicationResponse toResponse(InternshipApplication application) {
        Opportunity opportunity = application.getOpportunity();

        return new ApplicationResponse(
                application.getId(),
                opportunity.getId(),
                opportunity.getTitle(),
                opportunity.getOrganization(),
                opportunity.getCategory(),
                opportunity.getLocation(),
                opportunity.getDeadline(),
                application.getStatus());
    }
}