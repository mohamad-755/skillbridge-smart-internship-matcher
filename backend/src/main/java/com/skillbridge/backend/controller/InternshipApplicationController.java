package com.skillbridge.backend.controller;

import com.skillbridge.backend.dto.ApplicationResponse;
import com.skillbridge.backend.model.ApplicationStatus;
import com.skillbridge.backend.service.InternshipApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
public class InternshipApplicationController {

    private final InternshipApplicationService applicationService;

    public InternshipApplicationController(InternshipApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/{userId}/{opportunityId}")
    public ResponseEntity<?> createApplication(
            @PathVariable Integer userId,
            @PathVariable Integer opportunityId) {
        try {
            ApplicationResponse response = applicationService.createApplication(userId, opportunityId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getApplicationsForUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(applicationService.getApplicationsForUser(userId));
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Integer applicationId,
            @RequestParam ApplicationStatus status) {
        try {
            ApplicationResponse response = applicationService.updateStatus(applicationId, status);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<?> deleteApplication(@PathVariable Integer applicationId) {
        try {
            applicationService.deleteApplication(applicationId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}