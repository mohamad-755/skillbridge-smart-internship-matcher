package com.skillbridge.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.skillbridge.backend.model.Opportunity;
import com.skillbridge.backend.service.OpportunityService;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/opportunities")
public class OpportunityController {

    private final OpportunityService opportunityService;

    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @GetMapping
    public List<Opportunity> getAllOpportunities() {
        return opportunityService.getAllOpportunities();
    }

    @GetMapping("/{id}")
    public Opportunity getOpportunityById(@PathVariable int id) {
        return opportunityService.getOpportunityById(id);
    }

    @PostMapping
    public ResponseEntity<Opportunity> addOpportunity(@Valid @RequestBody Opportunity opportunity) {
        Opportunity saved = opportunityService.addOpportunity(opportunity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/search")
    public List<Opportunity> searchOpportunities(@RequestParam String keyword) {
        return opportunityService.searchOpportunities(keyword);
    }

    @GetMapping("/filter")
    public List<Opportunity> filterOpportunities(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String location) {
        return opportunityService.filterOpportunities(category, location);
    }

    @GetMapping("/sort")
    public List<Opportunity> sortOpportunities(@RequestParam String by) {
        return opportunityService.sortOpportunities(by);
    }
}