package com.doconnect.controller;

import com.doconnect.dto.QuestionDTO;
import com.doconnect.exception.CustomException;
import com.doconnect.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller
@RequestMapping("/questions") // Base URL for question-related endpoints
public class QuestionController {

    private final QuestionService questionService; // Service layer for question operations

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping // API to ask a new question
    public ResponseEntity<?> askQuestion(@RequestBody QuestionDTO questionDTO) {
        try {
            return ResponseEntity.ok(questionService.askQuestion(questionDTO));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping // API to fetch only approved questions
    public ResponseEntity<List<QuestionDTO>> getApprovedQuestions() {
        return ResponseEntity.ok(questionService.getApprovedQuestions());
    }

    @GetMapping("/all") // API to fetch all questions (approved + unapproved)
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/{id}") // API to fetch a question by its ID
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(questionService.getQuestionById(id));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}") // API to fetch questions asked by a specific user
    public ResponseEntity<List<QuestionDTO>> getQuestionsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(questionService.getQuestionsByUser(userId));
    }

    @PutMapping("/{id}/approve") // API to approve a question
    public ResponseEntity<?> approveQuestion(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(questionService.approveQuestion(id));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/reject") // API to reject a question
    public ResponseEntity<?> rejectQuestion(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(questionService.rejectQuestion(id));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search") // API to search questions by keyword
    public ResponseEntity<List<QuestionDTO>> searchQuestions(@RequestParam String keyword) {
        return ResponseEntity.ok(questionService.searchQuestions(keyword));
    }

    @PutMapping("/{id}/resolve") // API to mark a question as resolved
    public ResponseEntity<?> markAsResolved(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(questionService.markAsResolved(id));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}") // API to delete a question
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.ok("Question deleted successfully!");
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
