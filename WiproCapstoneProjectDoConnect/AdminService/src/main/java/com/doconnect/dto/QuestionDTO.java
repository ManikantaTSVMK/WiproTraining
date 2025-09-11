package com.doconnect.dto;

import java.util.List;

import lombok.Data;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
public class QuestionDTO {
    private Long id; // Unique identifier for the question
    private String title; // Title of the question
    private String description; // Detailed description of the question
    private Long userId; // ID of the user who asked the question
    private String askedBy; // Name or identifier of the user who asked
    private boolean approved; // Flag to check if the question is approved by admin
    private boolean resolved; // Flag to indicate if the question is resolved
    private List<AnswerDTO> answers; // List of answers associated with this question
}
