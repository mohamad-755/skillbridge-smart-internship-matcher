package com.skillbridge.backend.repository;

import com.skillbridge.backend.model.SavedOpportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedOpportunityRepository extends JpaRepository<SavedOpportunity, Integer> {
    List<SavedOpportunity> findByUserId(Integer userId);

    boolean existsByUserIdAndOpportunityId(Integer userId, Integer opportunityId);

    void deleteByUserIdAndOpportunityId(Integer userId, Integer opportunityId);
}