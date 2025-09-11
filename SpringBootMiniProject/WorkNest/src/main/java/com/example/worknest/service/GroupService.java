package com.example.worknest.service;

import com.example.worknest.model.Group;
import com.example.worknest.model.User;
import com.example.worknest.repository.GroupRepository;
import com.example.worknest.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepo;
    private final UserRepository userRepo;

    public Group createGroup(String name, List<Long> memberIds) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Group name is required");
        }
        if (memberIds == null || memberIds.isEmpty()) {
            throw new IllegalArgumentException("At least one member is required");
        }

        Set<User> members = userRepo.findAllById(memberIds).stream().collect(java.util.stream.Collectors.toSet());
        if (members.size() != memberIds.size()) {
            throw new IllegalArgumentException("Some users not found");
        }

        Group group = Group.builder()
                .name(name.trim())
                .members(members)
                .build();

        return groupRepo.save(group);
    }

    public Group getById(Long id) {
        return groupRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with ID " + id));
    }

    public List<Group> findAll() {
        return groupRepo.findByDeletedFalse();
    }

    public void delete(Long id) {
        Group group = groupRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with ID " + id));
        group.setDeleted(true);
        groupRepo.save(group);
    }

    public Group update(Group group) {
        return groupRepo.save(group);
    }

    /**
     * Create a group by user role
     */
    public Group createGroupByRole(String role, String groupName) {
        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Role is required");
        }
        if (groupName == null || groupName.isBlank()) {
            throw new IllegalArgumentException("Group name is required");
        }

        List<User> usersWithRole = userRepo.findByRole(role);
        if (usersWithRole.isEmpty()) {
            throw new IllegalArgumentException("No users found with role: " + role);
        }

        Set<User> members = new HashSet<>(usersWithRole);

        Group group = Group.builder()
                .name(groupName.trim())
                .members(members)
                .build();

        return groupRepo.save(group);
    }
}
