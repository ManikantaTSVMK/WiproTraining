package com.doconnect.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // Marks this class as a JPA entity
@Table(name = "answers") // Maps to the "answers" table
public class Answer {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    private Long id; // Unique identifier for the answer

    private String content;    // Answer content
    private String answeredBy; // Username or name of the person who answered
    private Long userId;       // User ID of the person who answered

    private boolean approved = false; // Flag for admin approval
    private int likes = 0;            // Number of likes on the answer

    @ElementCollection // Stores collection of comments in a separate table
    @CollectionTable(name = "answer_comments", joinColumns = @JoinColumn(name = "answer_id"))
    @Column(name = "comment") // Column to store individual comments
    private List<String> comments = new ArrayList<>(); // List of comments

    @ManyToOne(fetch = FetchType.LAZY) // Many answers belong to one question
    @JoinColumn(name = "question_id") // Foreign key linking to Question
    @JsonBackReference // Prevents infinite recursion in JSON serialization
    private Question question; // Associated question

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

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
}
