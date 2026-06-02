package com.skillbridge.backend.dto;

import java.util.List;

public class MatchResult {
    private int studentId;
    private int opportunityId;
    private String opportunityTitle;
    private String organization;
    private String category;
    private String location;
    private String deadline;
    private int matchScore;
    private int skillsScore;
    private int locationScore;
    private int interestScore;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private String reason;

    public MatchResult() {
    }

    public MatchResult(int studentId, int opportunityId, String opportunityTitle,
            String organization, String category, String location,
            String deadline, int matchScore, int skillsScore,
            int locationScore, int interestScore,
            List<String> matchedSkills, List<String> missingSkills, String reason) {
        this.studentId = studentId;
        this.opportunityId = opportunityId;
        this.opportunityTitle = opportunityTitle;
        this.organization = organization;
        this.category = category;
        this.location = location;
        this.deadline = deadline;
        this.matchScore = matchScore;
        this.skillsScore = skillsScore;
        this.locationScore = locationScore;
        this.interestScore = interestScore;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.reason = reason;
    }

    public int getSkillsScore() {
        return skillsScore;
    }

    public void setSkillsScore(int skillsScore) {
        this.skillsScore = skillsScore;
    }

    public int getLocationScore() {
        return locationScore;
    }

    public void setInterestScore(int interestScore) {
        this.interestScore = interestScore;
    }

    public int getInterestScore() {
        return interestScore;
    }

    public void setLocationScore(int locationScore) {
        this.locationScore = locationScore;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(int opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getOpportunityTitle() {
        return opportunityTitle;
    }

    public void setOpportunityTitle(String opportunityTitle) {
        this.opportunityTitle = opportunityTitle;
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

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}