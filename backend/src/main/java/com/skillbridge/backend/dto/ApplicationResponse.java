package com.skillbridge.backend.dto;

import com.skillbridge.backend.model.ApplicationStatus;

public class ApplicationResponse {
    private Integer id;
    private Integer opportunityId;
    private String title;
    private String organization;
    private String category;
    private String location;
    private String deadline;
    private ApplicationStatus status;

    public ApplicationResponse() {
    }

    public ApplicationResponse(Integer id, Integer opportunityId, String title,
            String organization, String category, String location,
            String deadline, ApplicationStatus status) {
        this.id = id;
        this.opportunityId = opportunityId;
        this.title = title;
        this.organization = organization;
        this.category = category;
        this.location = location;
        this.deadline = deadline;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public String getTitle() {
        return title;
    }

    public String getOrganization() {
        return organization;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public String getDeadline() {
        return deadline;
    }

    public ApplicationStatus getStatus() {
        return status;
    }
}