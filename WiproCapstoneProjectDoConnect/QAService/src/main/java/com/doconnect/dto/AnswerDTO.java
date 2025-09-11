package com.doconnect.dto;

import java.util.ArrayList;
import java.util.List;

public class AnswerDTO {

    private Long id; // Unique identifier for the answer
    private String content; // Content of the answer
    private String answeredBy; // Name/username of the user who answered
    private Long userId; // ID of the user who answered
    private boolean approved; // Flag to check if the answer is approved

    // Extra fields
    private int likes = 0; // Number of likes the answer has received
    private List<String> comments = new ArrayList<>(); // List of comments on this answer
    private Long questionId; // ID of the question this answer belongs to

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAnsweredBy() { return answeredBy; }
    public void setAnsweredBy(String answeredBy) { this.answeredBy = answeredBy; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public List<String> getComments() { return comments; }
    public void setComments(List<String> comments) { this.comments = comments; }

    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }
}
