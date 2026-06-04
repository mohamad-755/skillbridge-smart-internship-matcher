package com.skillbridge.backend.controller;

import com.skillbridge.backend.service.MatchService;
import com.skillbridge.backend.dto.MatchResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{studentId}/{opportunityId}")
    public ResponseEntity<MatchResult> matchStudentWithOpportunity(
            @PathVariable int studentId,
            @PathVariable int opportunityId) {
        MatchResult result = matchService.matchStudentWithOpportunity(studentId, opportunityId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{studentId}/all")
    public ResponseEntity<List<MatchResult>> getAllMatchesForStudent(
            @PathVariable int studentId) {
        List<MatchResult> results = matchService.getRecommendationsForStudent(studentId);
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(results);
    }
}