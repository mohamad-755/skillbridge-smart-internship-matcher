package com.skillbridge.backend.model;

import jakarta.persistence.*;

@Entity
public class InternshipApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Opportunity opportunity;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    public InternshipApplication() {
    }

    public InternshipApplication(Integer id, User user, Opportunity opportunity, ApplicationStatus status) {
        this.id = id;
        this.user = user;
        this.opportunity = opportunity;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}