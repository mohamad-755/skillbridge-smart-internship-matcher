package com.skillbridge.backend.controller;

import com.skillbridge.backend.dto.SavedOpportunityResponse;
import com.skillbridge.backend.model.User;
import com.skillbridge.backend.security.AuthContext;
import com.skillbridge.backend.service.SavedOpportunityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saved-opportunities")
public class SavedOpportunityController {

    private final SavedOpportunityService savedOpportunityService;
    private final AuthContext authContext;

    public SavedOpportunityController(
            SavedOpportunityService savedOpportunityService,
            AuthContext authContext) {
        this.savedOpportunityService = savedOpportunityService;
        this.authContext = authContext;
    }

    @PostMapping("/me/{opportunityId}")
    public ResponseEntity<SavedOpportunityResponse> saveOpportunity(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @PathVariable Integer opportunityId) {
        User user = authContext.getUserFromAuthorizationHeader(authorizationHeader);
        SavedOpportunityResponse savedOpportunity = savedOpportunityService.saveOpportunity(user.getId(),
                opportunityId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOpportunity);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getSavedOpportunities(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        User user = authContext.getUserFromAuthorizationHeader(authorizationHeader);
        return ResponseEntity.ok(savedOpportunityService.getSavedOpportunities(user.getId()));
    }

    @DeleteMapping("/me/{opportunityId}")
    public ResponseEntity<?> unsaveOpportunity(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @PathVariable Integer opportunityId) {
        User user = authContext.getUserFromAuthorizationHeader(authorizationHeader);
        savedOpportunityService.unsaveOpportunity(user.getId(), opportunityId);
        return ResponseEntity.noContent().build();
    }
}