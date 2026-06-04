package com.skillbridge.backend;

import com.skillbridge.backend.dto.MatchResult;
import com.skillbridge.backend.model.Opportunity;
import com.skillbridge.backend.model.Student;
import com.skillbridge.backend.service.MatchService;
import com.skillbridge.backend.service.OpportunityService;
import com.skillbridge.backend.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    private StudentService studentService;

    @Mock
    private OpportunityService opportunityService;

    @InjectMocks
    private MatchService matchService;

    private Student student;
    private Opportunity opportunity;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1);
        student.setName("Mohamad");
        student.setLocation("Beirut");
        student.setSkills(Arrays.asList("Java", "Git", "OOP"));
        student.setInterests(Arrays.asList("Backend", "Internship"));

        opportunity = new Opportunity();
        opportunity.setId(1);
        opportunity.setTitle("Backend Internship");
        opportunity.setOrganization("TechStart");
        opportunity.setCategory("Internship");
        opportunity.setLocation("Beirut");
        opportunity.setDeadline("2026-07-15");
        opportunity.setDescription("A backend internship for CS students.");
        opportunity.setRequiredSkills(Arrays.asList("Java", "Spring Boot", "Git"));
    }

    @Test
    void testMatchScore_isNotZero() {
        when(studentService.getStudentById(1)).thenReturn(student);
        when(opportunityService.getOpportunityById(1)).thenReturn(opportunity);

        MatchResult result = matchService.matchStudentWithOpportunity(1, 1);

        assertNotNull(result);
        assertTrue(result.getMatchScore() > 0);
    }

    @Test
    void testMatchedSkills_containsJavaAndGit() {
        when(studentService.getStudentById(1)).thenReturn(student);
        when(opportunityService.getOpportunityById(1)).thenReturn(opportunity);

        MatchResult result = matchService.matchStudentWithOpportunity(1, 1);

        assertTrue(result.getMatchedSkills().contains("Java"));
        assertTrue(result.getMatchedSkills().contains("Git"));
    }

    @Test
    void testMissingSkills_containsSpringBoot() {
        when(studentService.getStudentById(1)).thenReturn(student);
        when(opportunityService.getOpportunityById(1)).thenReturn(opportunity);

        MatchResult result = matchService.matchStudentWithOpportunity(1, 1);

        assertTrue(result.getMissingSkills().contains("Spring Boot"));
    }

    @Test
    void testLearningRoadmap_notEmpty_whenMissingSkills() {
        when(studentService.getStudentById(1)).thenReturn(student);
        when(opportunityService.getOpportunityById(1)).thenReturn(opportunity);

        MatchResult result = matchService.matchStudentWithOpportunity(1, 1);

        assertFalse(result.getLearningRoadmap().isEmpty());
    }

    @Test
    void testMatch_returnsNull_whenStudentNotFound() {
        when(studentService.getStudentById(99)).thenReturn(null);

        MatchResult result = matchService.matchStudentWithOpportunity(99, 1);

        assertNull(result);
    }

    @Test
    void testBatchMatcher_returnsSortedByMatchScore() {
        Opportunity lowMatch = new Opportunity();
        lowMatch.setId(2);
        lowMatch.setTitle("Frontend Program");
        lowMatch.setOrganization("CodeForGood");
        lowMatch.setCategory("Volunteering");
        lowMatch.setLocation("Remote");
        lowMatch.setDeadline("2026-06-20");
        lowMatch.setDescription("Frontend volunteering.");
        lowMatch.setRequiredSkills(Arrays.asList("HTML", "CSS", "React"));

        when(studentService.getStudentById(1)).thenReturn(student);
        when(opportunityService.getAllOpportunities()).thenReturn(Arrays.asList(lowMatch, opportunity));

        List<MatchResult> results = matchService.getRecommendationsForStudent(1);

        assertFalse(results.isEmpty());
        assertTrue(results.get(0).getMatchScore() >= results.get(1).getMatchScore());
    }
}