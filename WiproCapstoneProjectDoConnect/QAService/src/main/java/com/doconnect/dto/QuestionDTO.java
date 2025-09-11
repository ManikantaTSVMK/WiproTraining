package com.doconnect.dto;

import java.util.List;

public class QuestionDTO {

    private Long id; // Unique identifier for the question
    private String title; // Title of the question
    private String description; // Detailed description of the question
    private Long userId; // ID of the user who asked the question
    private String askedBy; // Username or name of the person who asked
    private boolean approved; // Flag to check if the question is approved by admin
    private boolean resolved; // Flag to track if the question is resolved
    private List<AnswerDTO> answers; // Holds related answers for this question

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

    public List<AnswerDTO> getAnswers() { return answers; }
    public void setAnswers(List<AnswerDTO> answers) { this.answers = answers; }
}
