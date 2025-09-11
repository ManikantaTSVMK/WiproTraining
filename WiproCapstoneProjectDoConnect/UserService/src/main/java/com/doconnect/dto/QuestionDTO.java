package com.doconnect.dto;

import java.util.List;

import lombok.Data;

@Data // Lombok annotation to generate boilerplate code (getters, setters, etc.)
public class QuestionDTO {
    private Long id; // Unique identifier for the question
    private String title; // Title of the question
    private String description; // Description/body of the question
    private Long userId; // ID of the user who asked the question
    private String askedBy; // Name/username of the user who asked
    private boolean approved; // Flag to indicate if the question is approved by admin

    // New field
    private boolean resolved; // Flag to indicate if the question has been resolved

    private List<AnswerDTO> answers; // List of answers related to this question
}
