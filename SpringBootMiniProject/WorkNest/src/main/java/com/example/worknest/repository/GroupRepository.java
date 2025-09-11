package com.example.worknest.repository;

import com.example.worknest.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    // Add custom queries if needed

    // Soft delete support: find all non-deleted groups
    List<Group> findByDeletedFalse();
}
