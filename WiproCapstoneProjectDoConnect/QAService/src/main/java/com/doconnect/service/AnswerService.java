package com.doconnect.service;

import com.doconnect.dto.AnswerDTO;
import java.util.List;

public interface AnswerService {

    AnswerDTO giveAnswer(Long questionId, AnswerDTO answerDTO); // Submit a new answer for a question

    List<AnswerDTO> getAllAnswersForQuestion(Long questionId); // Get all answers for a specific question

    List<AnswerDTO> getAllAnswers(); // Get all answers (admin view)

    AnswerDTO approveAnswer(Long id); // Approve an answer (admin action)

    AnswerDTO rejectAnswer(Long id); // Reject an answer (admin action)

    AnswerDTO likeAnswer(Long id); // Like an answer

    AnswerDTO addComment(Long id, String comment); // Add a comment to an answer

    //  New: Delete Answer
    void deleteAnswer(Long id); // Delete an answer (admin only)
}
