package com.skillbridge.backend.service;

import com.skillbridge.backend.model.Student;
import com.skillbridge.backend.model.Opportunity;
import com.skillbridge.backend.dto.MatchResult;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MatchService {

    private StudentService studentService;
    private OpportunityService opportunityService;

    public MatchService(StudentService studentService, OpportunityService opportunityService) {
        this.studentService = studentService;
        this.opportunityService = opportunityService;
    }

    public MatchResult matchStudentWithOpportunity(int studentId, int opportunityId) {
        Student student = studentService.getStudentById(studentId);
        Opportunity opportunity = opportunityService.getOpportunityById(opportunityId);

        if (student == null || opportunity == null) {
            return null;
        }

        return calculateMatch(student, opportunity);
    }

    public List<MatchResult> getRecommendationsForStudent(int studentId) {
        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            return new ArrayList<>();
        }

        List<MatchResult> results = new ArrayList<>();

        for (Opportunity opportunity : opportunityService.getAllOpportunities()) {
            MatchResult result = calculateMatch(student, opportunity);
            results.add(result);
        }

        results.sort(Comparator.comparingInt(MatchResult::getMatchScore).reversed());

        return results;
    }

    private MatchResult calculateMatch(Student student, Opportunity opportunity) {
        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String requiredSkill : opportunity.getRequiredSkills()) {
            if (student.getSkills().contains(requiredSkill)) {
                matchedSkills.add(requiredSkill);
            } else {
                missingSkills.add(requiredSkill);
            }
        }

        int totalRequiredSkills = opportunity.getRequiredSkills().size();
        int matchedCount = matchedSkills.size();

        int skillsScore = 0;

        if (totalRequiredSkills > 0) {
            skillsScore = (matchedCount * 70) / totalRequiredSkills;
        }

        int locationScore = 0;

        if (student.getLocation().equalsIgnoreCase(opportunity.getLocation())
                || opportunity.getLocation().equalsIgnoreCase("Remote")) {
            locationScore = 15;
        }

        int interestScore = 0;

        if (student.getInterests() != null) {
            for (String interest : student.getInterests()) {
                if (interest.equalsIgnoreCase(opportunity.getCategory())
                        || opportunity.getTitle().toLowerCase().contains(interest.toLowerCase())
                        || opportunity.getDescription().toLowerCase().contains(interest.toLowerCase())) {
                    interestScore = 15;
                    break;
                }
            }
        }

        int matchScore = skillsScore + locationScore + interestScore;

        return new MatchResult(
                student.getId(),
                opportunity.getId(),
                opportunity.getTitle(),
                opportunity.getOrganization(),
                opportunity.getCategory(),
                opportunity.getLocation(),
                opportunity.getDeadline(),
                matchScore,
                skillsScore,
                locationScore,
                interestScore,
                matchedSkills,
                missingSkills);
    }
}