package com.skillbridge.backend.controller;

import com.skillbridge.backend.dto.ApplicationResponse;
import com.skillbridge.backend.model.ApplicationStatus;
import com.skillbridge.backend.model.User;
import com.skillbridge.backend.security.AuthContext;
import com.skillbridge.backend.service.InternshipApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applications")
public class InternshipApplicationController {

    private final InternshipApplicationService applicationService;
    private final AuthContext authContext;

    public InternshipApplicationController(
            InternshipApplicationService applicationService,
            AuthContext authContext) {
        this.applicationService = applicationService;
        this.authContext = authContext;
    }

    @PostMapping("/me/{opportunityId}")
    public ResponseEntity<ApplicationResponse> createApplication(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @PathVariable Integer opportunityId) {
        User user = authContext.getUserFromAuthorizationHeader(authorizationHeader);
        ApplicationResponse response = applicationService.createApplication(user.getId(), opportunityId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getApplicationsForUser(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        User user = authContext.getUserFromAuthorizationHeader(authorizationHeader);
        return ResponseEntity.ok(applicationService.getApplicationsForUser(user.getId()));
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @PathVariable Integer applicationId,
            @RequestParam ApplicationStatus status) {
        User user = authContext.getUserFromAuthorizationHeader(authorizationHeader);
        ApplicationResponse response = applicationService.updateStatus(user.getId(), applicationId, status);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<?> deleteApplication(
            @RequestHeader(value = "Authorization", required = false) String authorizationHeader,
            @PathVariable Integer applicationId) {
        User user = authContext.getUserFromAuthorizationHeader(authorizationHeader);
        applicationService.deleteApplication(user.getId(), applicationId);
        return ResponseEntity.noContent().build();
    }
}