package com.skillbridge.backend.repository;

import com.skillbridge.backend.model.InternshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipApplicationRepository extends JpaRepository<InternshipApplication, Integer> {
    List<InternshipApplication> findByUserId(Integer userId);

    boolean existsByUserIdAndOpportunityId(Integer userId, Integer opportunityId);
}