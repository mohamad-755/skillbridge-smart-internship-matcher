package com.skillbridge.backend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private MatchService matchService;

    public RecommendationController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{studentId}")
    public List<MatchResult> getRecommendationsForStudent(@PathVariable int studentId) {
        return matchService.getRecommendationsForStudent(studentId);
    }
}