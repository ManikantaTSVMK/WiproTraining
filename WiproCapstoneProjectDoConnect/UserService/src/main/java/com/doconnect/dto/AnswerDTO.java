package com.doconnect.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
public class AnswerDTO {
    private Long id; // Unique identifier for the answer
    private String content; // Content/body of the answer
    private String answeredBy; // Name/username of the person who answered
    private Long userId; // ID of the user who answered
    private boolean approved; // Flag to indicate if answer is approved by admin

    // New fields
    private int likes = 0; // Number of likes on the answer
    private List<String> comments = new ArrayList<>(); // List of comments on the answer
}
