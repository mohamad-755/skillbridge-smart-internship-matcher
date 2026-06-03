package com.skillbridge.backend.repository;

import com.skillbridge.backend.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
    java.util.List<Opportunity> findByCategoryContainingIgnoreCase(String category);

    java.util.List<Opportunity> findByLocationContainingIgnoreCase(String location);
}