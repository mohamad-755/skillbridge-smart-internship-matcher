package com.skillbridge.backend.model;

import jakarta.persistence.*;

@Entity
public class SavedOpportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Opportunity opportunity;

    public SavedOpportunity() {
    }

    public SavedOpportunity(Integer id, User user, Opportunity opportunity) {
        this.id = id;
        this.user = user;
        this.opportunity = opportunity;
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
}