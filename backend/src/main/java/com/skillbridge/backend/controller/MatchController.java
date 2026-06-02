package com.skillbridge.backend.controller;

import com.skillbridge.backend.service.MatchService;
import com.skillbridge.backend.dto.MatchResult;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class MatchController {

    private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{studentId}/{opportunityId}")
    public MatchResult matchStudentWithOpportunity(@PathVariable int studentId,
            @PathVariable int opportunityId) {
        return matchService.matchStudentWithOpportunity(studentId, opportunityId);
    }
}