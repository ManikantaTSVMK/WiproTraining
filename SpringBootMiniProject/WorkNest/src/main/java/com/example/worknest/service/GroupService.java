package com.example.worknest.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.worknest.model.Group;
import com.example.worknest.model.User;
import com.example.worknest.repository.GroupRepository;
import com.example.worknest.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

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
        return groupRepo.findAll();
    }

    public void delete(Long id) {
        if (!groupRepo.existsById(id)) {
            throw new EntityNotFoundException("Group not found with ID " + id);
        }
        groupRepo.deleteById(id);
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
