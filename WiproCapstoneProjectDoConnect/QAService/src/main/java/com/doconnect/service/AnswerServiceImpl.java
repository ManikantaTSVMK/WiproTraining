package com.doconnect.service;

import com.doconnect.dto.AnswerDTO;
import com.doconnect.dto.NotificationRequest;
import com.doconnect.entity.Answer;
import com.doconnect.entity.Question;
import com.doconnect.exception.CustomException;
import com.doconnect.feign.NotificationFeignClient;
import com.doconnect.repository.AnswerRepository;
import com.doconnect.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Marks this as a Spring service component
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository; // Repository for Answer entity
    private final QuestionRepository questionRepository; // Repository for Question entity
    private final NotificationFeignClient notificationFeignClient; // Feign client for notifications

    public AnswerServiceImpl(AnswerRepository answerRepository,
                             QuestionRepository questionRepository,
                             NotificationFeignClient notificationFeignClient) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.notificationFeignClient = notificationFeignClient;
    }

    @Override
    public AnswerDTO giveAnswer(Long questionId, AnswerDTO answerDTO) {
        // Validate question existence
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException("Question not found with id " + questionId));

        // Validate answer content
        if (answerDTO.getContent() == null || answerDTO.getContent().isBlank()) {
            throw new CustomException("Answer content cannot be empty.");
        }

        // Create new Answer entity
        Answer answer = new Answer();
        answer.setContent(answerDTO.getContent().trim());
        answer.setAnsweredBy(answerDTO.getAnsweredBy() != null ? answerDTO.getAnsweredBy() : "Anonymous");
        answer.setUserId(answerDTO.getUserId());
        answer.setApproved(false);
        answer.setLikes(0);
        answer.setQuestion(question);

        Answer saved = answerRepository.save(answer);

        //  Notify Admin about new answer submission
        try {
            notificationFeignClient.sendNotification(
                    new NotificationRequest(
                            "admin@gmail.com", // TODO: fetch dynamically from AdminService
                            "💡 New Answer Submitted",
                            "Answer: " + saved.getContent() +
                                    "\nAnswered by: " + saved.getAnsweredBy() +
                                    "\nQuestion: " + question.getTitle()
                    )
            );
            System.out.println("✅ Notification sent for new answer: " + saved.getId());
        } catch (Exception e) {
            System.err.println("⚠️ Failed to send notification: " + e.getMessage());
        }

        return mapToDTO(saved);
    }

    @Override
    public List<AnswerDTO> getAllAnswersForQuestion(Long questionId) {
        // Validate question existence
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException("Question not found with id " + questionId));

        // Return answers for this question
        return question.getAnswers().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnswerDTO> getAllAnswers() {
        // Return all answers
        return answerRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AnswerDTO approveAnswer(Long id) {
        // Find answer
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Answer not found with id " + id));
        answer.setApproved(true);

        Answer saved = answerRepository.save(answer);

        //  Notify user about approval
        try {
            notificationFeignClient.sendNotification(
                    new NotificationRequest(
                            "user@gmail.com", // TODO: fetch dynamically from UserService
                            "✅ Your Answer Approved",
                            "Your answer to the question '" +
                                    saved.getQuestion().getTitle() +
                                    "' has been approved by Admin."
                    )
            );
        } catch (Exception e) {
            System.err.println("⚠️ Failed to send approval notification: " + e.getMessage());
        }

        return mapToDTO(saved);
    }

    @Override
    public AnswerDTO rejectAnswer(Long id) {
        // Find answer
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Answer not found with id " + id));
        answer.setApproved(false);
        return mapToDTO(answerRepository.save(answer));
    }

    @Override
    public AnswerDTO likeAnswer(Long id) {
        // Increment likes
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Answer not found with id " + id));
        answer.setLikes(answer.getLikes() + 1);
        return mapToDTO(answerRepository.save(answer));
    }

    @Override
    public AnswerDTO addComment(Long id, String comment) {
        // Find answer
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Answer not found with id " + id));

        // Validate comment
        if (comment == null || comment.isBlank()) {
            throw new CustomException("Comment cannot be empty.");
        }

        // Add comment
        answer.getComments().add(comment.trim());
        return mapToDTO(answerRepository.save(answer));
    }

    @Override
    public void deleteAnswer(Long id) {
        // Find answer and delete
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Answer not found with id " + id));
        answerRepository.delete(answer);
    }

    // Helper: Convert Answer entity to DTO
    private AnswerDTO mapToDTO(Answer a) {
        if (a == null) return null;

        AnswerDTO dto = new AnswerDTO();
        dto.setId(a.getId());
        dto.setContent(a.getContent());
        dto.setAnsweredBy(a.getAnsweredBy());
        dto.setUserId(a.getUserId());
        dto.setApproved(a.isApproved());
        dto.setLikes(a.getLikes());
        dto.setComments(a.getComments() != null ? a.getComments() : List.of());
        dto.setQuestionId(a.getQuestion() != null ? a.getQuestion().getId() : null);

        return dto;
    }
}
