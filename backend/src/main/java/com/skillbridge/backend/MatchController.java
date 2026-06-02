package com.skillbridge.backend;

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