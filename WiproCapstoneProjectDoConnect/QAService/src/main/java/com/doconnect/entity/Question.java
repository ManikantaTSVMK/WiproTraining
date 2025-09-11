package com.doconnect.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // Marks this class as a JPA entity
@Table(name = "questions") // Maps to the "questions" table
public class Question {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id; // Unique identifier for the question

    private String title;       // Question title
    private String description; // Question description
    private Long userId;        // ID of the user who asked the question
    private String askedBy;     // Username or name of the user who asked

    private boolean approved = false; // Flag to indicate if approved by admin
    private boolean resolved = false; // Flag to indicate if marked as resolved

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true) // One question -> many answers
    @JsonManagedReference // Handles bidirectional JSON serialization with Answer
    private List<Answer> answers = new ArrayList<>(); // List of answers linked to this question

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getAskedBy() { return askedBy; }
    public void setAskedBy(String askedBy) { this.askedBy = askedBy; }

    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }

    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }
}
