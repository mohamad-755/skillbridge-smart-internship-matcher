package com.skillbridge.backend.model;

import jakarta.persistence.*;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Organization is required")
    private String organization;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Deadline is required")
    private String deadline;

    @NotBlank(message = "Description is required")
    private String description;

    @NotEmpty(message = "At least one required skill is required")
    @ElementCollection
    private List<String> requiredSkills;

    public Opportunity() {
    }

    public Opportunity(Integer id, String title, String organization, String category,
            String location, String deadline, String description,
            List<String> requiredSkills) {
        this.id = id;
        this.title = title;
        this.organization = organization;
        this.category = category;
        this.location = location;
        this.deadline = deadline;
        this.description = description;
        this.requiredSkills = requiredSkills;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}