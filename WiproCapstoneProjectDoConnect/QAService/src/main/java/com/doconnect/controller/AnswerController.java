package com.doconnect.controller;

import com.doconnect.dto.AnswerDTO;
import com.doconnect.exception.CustomException;
import com.doconnect.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller
@RequestMapping("/answers") // Base URL for answer-related endpoints
public class AnswerController {

    private final AnswerService answerService; // Service layer for answer operations

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    // ==============================
    //  Create Answer
    // ==============================
    @PostMapping("/{questionId}") // API to submit an answer for a question
    public ResponseEntity<?> giveAnswer(@PathVariable Long questionId, @RequestBody AnswerDTO answerDTO) {
        try {
            return ResponseEntity.ok(answerService.giveAnswer(questionId, answerDTO));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ==============================
    //  Get Answers
    // ==============================
    @GetMapping("/question/{questionId}") // API to get all answers for a specific question
    public ResponseEntity<?> getAllAnswersForQuestion(@PathVariable Long questionId) {
        try {
            return ResponseEntity.ok(answerService.getAllAnswersForQuestion(questionId));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping // API to fetch all answers
    public List<AnswerDTO> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    // ==============================
    //  Admin Approvals
    // ==============================
    @PutMapping("/{id}/approve") // API to approve an answer
    public ResponseEntity<?> approveAnswer(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(answerService.approveAnswer(id));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/reject") // API to reject an answer
    public ResponseEntity<?> rejectAnswer(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(answerService.rejectAnswer(id));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ==============================
    // Like & Comment
    // ==============================
    @PutMapping("/{id}/like") // API to like an answer
    public ResponseEntity<?> likeAnswer(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(answerService.likeAnswer(id));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/comment") // API to add a comment to an answer
    public ResponseEntity<?> addComment(@PathVariable Long id, @RequestParam String comment) {
        try {
            return ResponseEntity.ok(answerService.addComment(id, comment));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ==============================
    //  Delete (Admin only)
    // ==============================
    @DeleteMapping("/{id}") // API to delete an answer
    public ResponseEntity<String> deleteAnswer(@PathVariable Long id) {
        try {
            answerService.deleteAnswer(id);
            return ResponseEntity.ok("Answer deleted successfully!");
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
