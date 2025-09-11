package com.doconnect.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.doconnect.dto.AnswerDTO;
import com.doconnect.dto.QuestionDTO;
import com.doconnect.dto.NotificationRequest;
import com.doconnect.entity.Answer;
import com.doconnect.entity.Question;
import com.doconnect.entity.User;
import com.doconnect.exception.CustomException;
import com.doconnect.feign.UserFeignClient;
import com.doconnect.feign.NotificationFeignClient;
import com.doconnect.repository.QuestionRepository;

@Service // Marks this as a Spring service component
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository; // Repository for Question entity
    private final UserFeignClient userFeignClient; // Feign client to fetch user details
    private final NotificationFeignClient notificationFeignClient; // Feign client to send notifications

    public QuestionServiceImpl(QuestionRepository questionRepository,
                               UserFeignClient userFeignClient,
                               NotificationFeignClient notificationFeignClient) {
        this.questionRepository = questionRepository;
        this.userFeignClient = userFeignClient;
        this.notificationFeignClient = notificationFeignClient;
    }

    // Ask Question
    @Override
    public QuestionDTO askQuestion(QuestionDTO questionDTO) {
        // Validate required fields
        if (questionDTO.getUserId() == null) {
            throw new CustomException("UserId is required.");
        }
        if (questionDTO.getTitle() == null || questionDTO.getTitle().isBlank()) {
            throw new CustomException("Question title cannot be empty.");
        }
        if (questionDTO.getDescription() == null || questionDTO.getDescription().isBlank()) {
            throw new CustomException("Question description cannot be empty.");
        }

        // Validate user existence
        User user = userFeignClient.getUserById(questionDTO.getUserId());
        if (user == null) {
            throw new CustomException("Invalid user. Cannot post question.");
        }

        // Create Question entity
        Question question = new Question();
        question.setTitle(questionDTO.getTitle().trim());
        question.setDescription(questionDTO.getDescription().trim());
        question.setUserId(questionDTO.getUserId());
        question.setAskedBy(questionDTO.getAskedBy() != null ? questionDTO.getAskedBy() : user.getName());
        question.setApproved(false);
        question.setResolved(false);

        Question saved = questionRepository.save(question);

        // Notify Admin (new question asked)
        try {
            notificationFeignClient.sendNotification(
                    new NotificationRequest(
                            "admin@gmail.com", // TODO: Fetch dynamically from AdminService
                            "📢 New Question Asked",
                            "Question ID: " + saved.getId() +
                                    "\nTitle: " + saved.getTitle() +
                                    "\nAsked by: " + saved.getAskedBy()
                    )
            );
            System.out.println("✅ Notification sent for new question: " + saved.getId());
        } catch (Exception e) {
            System.err.println("⚠️ Failed to send notification: " + e.getMessage());
        }

        return mapToDTO(saved);
    }

    // Get all questions
    @Override
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get only approved questions
    @Override
    public List<QuestionDTO> getApprovedQuestions() {
        return questionRepository.findByApprovedTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get a question by its ID
    @Override
    public QuestionDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new CustomException("Question not found with id " + id));
        return mapToDTO(question);
    }

    // Get questions asked by a specific user
    @Override
    public List<QuestionDTO> getQuestionsByUser(Long userId) {
        return questionRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Approve a question
    @Override
    public QuestionDTO approveQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new CustomException("Question not found with id " + id));
        question.setApproved(true);

        Question saved = questionRepository.save(question);

        // Notify user about approval
        try {
            User user = userFeignClient.getUserById(saved.getUserId());
            if (user != null) {
                notificationFeignClient.sendNotification(
                        new NotificationRequest(
                                user.getEmail(),
                                "✅ Question Approved",
                                "Your question '" + saved.getTitle() + "' has been approved by Admin."
                        )
                );
                System.out.println("✅ Notification sent for approved question: " + saved.getId());
            }
        } catch (Exception e) {
            System.err.println("⚠️ Failed to send approval notification: " + e.getMessage());
        }

        return mapToDTO(saved);
    }

    // Reject a question
    @Override
    public QuestionDTO rejectQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new CustomException("Question not found with id " + id));
        question.setApproved(false);
        return mapToDTO(questionRepository.save(question));
    }

    // Search questions by keyword
    @Override
    public List<QuestionDTO> searchQuestions(String keyword) {
        return questionRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Mark a question as resolved
    @Override
    public QuestionDTO markAsResolved(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new CustomException("Question not found with id " + id));
        question.setResolved(true);
        return mapToDTO(questionRepository.save(question));
    }

    // Delete a question
    @Override
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new CustomException("Question not found with id " + id));
        questionRepository.delete(question);
    }

    // Entity → DTO mapper
    private QuestionDTO mapToDTO(Question q) {
        if (q == null) return null;

        QuestionDTO dto = new QuestionDTO();
        dto.setId(q.getId());
        dto.setTitle(q.getTitle());
        dto.setDescription(q.getDescription());
        dto.setUserId(q.getUserId());
        dto.setAskedBy(q.getAskedBy());
        dto.setApproved(q.isApproved());
        dto.setResolved(q.isResolved());

        if (q.getAnswers() != null && !q.getAnswers().isEmpty()) {
            dto.setAnswers(
                    q.getAnswers().stream()
                            .map(this::mapAnswerToDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // Map Answer entity → AnswerDTO
    private AnswerDTO mapAnswerToDTO(Answer a) {
        if (a == null) return null;

        AnswerDTO dto = new AnswerDTO();
        dto.setId(a.getId());
        dto.setContent(a.getContent());
        dto.setAnsweredBy(a.getAnsweredBy());
        dto.setUserId(a.getUserId());
        dto.setApproved(a.isApproved());
        dto.setLikes(a.getLikes());
        dto.setComments(a.getComments() != null ? a.getComments() : List.of());
        return dto;
    }
}
