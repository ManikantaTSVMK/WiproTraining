package com.doconnect.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
public class AnswerDTO {

    private Long id; // Unique identifier for the answer
    private String content; // Content of the answer
    private String answeredBy; // Name or identifier of the person who answered
    private Long userId; // User ID of the person who answered
    private boolean approved; // Flag to check if the answer is approved by admin

    // Extra fields
    private int likes = 0; // Number of likes on the answer                         
    private List<String> comments = new ArrayList<>(); // List of comments on the answer 
    private Long questionId; // ID of the question this answer belongs to                          
    
}
