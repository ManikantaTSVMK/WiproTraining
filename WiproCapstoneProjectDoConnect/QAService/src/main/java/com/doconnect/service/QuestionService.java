package com.doconnect.service;

import com.doconnect.dto.QuestionDTO;
import java.util.List;

public interface QuestionService {

    QuestionDTO askQuestion(QuestionDTO questionDTO); // Ask a new question

    List<QuestionDTO> getAllQuestions(); // Get all questions (approved + unapproved)

    List<QuestionDTO> getApprovedQuestions(); // Get only approved questions

    QuestionDTO getQuestionById(Long id); // Get a question by its ID

    List<QuestionDTO> getQuestionsByUser(Long userId); // Get all questions asked by a specific user

    QuestionDTO approveQuestion(Long id); // Approve a question (admin action)

    QuestionDTO rejectQuestion(Long id); // Reject a question (admin action)

    List<QuestionDTO> searchQuestions(String keyword); // Search questions by keyword (title/description)

    QuestionDTO markAsResolved(Long id); // Mark a question as resolved

    void deleteQuestion(Long id); // Delete a question (admin only)
}
