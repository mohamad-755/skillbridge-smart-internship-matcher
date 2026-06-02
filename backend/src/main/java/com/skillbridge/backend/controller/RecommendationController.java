package com.skillbridge.backend.controller;

import com.skillbridge.backend.service.MatchService;
import com.skillbridge.backend.dto.MatchResult;
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