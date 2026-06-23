package com.skillbridge.backend.controller;

import com.skillbridge.backend.dto.MatchResult;
import com.skillbridge.backend.model.User;
import com.skillbridge.backend.security.AuthContext;
import com.skillbridge.backend.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;
    private final AuthContext authContext;

    public MatchController(MatchService matchService, AuthContext authContext) {
        this.matchService = matchService;
        this.authContext = authContext;
    }

    @GetMapping("/me")
    public ResponseEntity<List<MatchResult>> getMyMatches(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        User user = authContext.getUserFromAuthorizationHeader(authorizationHeader);
        List<MatchResult> results = matchService.getRecommendationsForUser(user.getId());
        return ResponseEntity.ok(results);
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