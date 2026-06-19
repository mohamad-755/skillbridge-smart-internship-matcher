package com.skillbridge.backend.controller;

import com.skillbridge.backend.dto.SavedOpportunityResponse;
import com.skillbridge.backend.service.SavedOpportunityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saved-opportunities")
public class SavedOpportunityController {

    private final SavedOpportunityService savedOpportunityService;

    public SavedOpportunityController(SavedOpportunityService savedOpportunityService) {
        this.savedOpportunityService = savedOpportunityService;
    }

    @PostMapping("/{userId}/{opportunityId}")
    public ResponseEntity<?> saveOpportunity(
            @PathVariable Integer userId,
            @PathVariable Integer opportunityId) {
        try {
            SavedOpportunityResponse savedOpportunity = savedOpportunityService.saveOpportunity(userId, opportunityId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOpportunity);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getSavedOpportunities(@PathVariable Integer userId) {
        return ResponseEntity.ok(savedOpportunityService.getSavedOpportunities(userId));
    }

    @DeleteMapping("/{userId}/{opportunityId}")
    public ResponseEntity<?> unsaveOpportunity(
            @PathVariable Integer userId,
            @PathVariable Integer opportunityId) {
        try {
            savedOpportunityService.unsaveOpportunity(userId, opportunityId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}