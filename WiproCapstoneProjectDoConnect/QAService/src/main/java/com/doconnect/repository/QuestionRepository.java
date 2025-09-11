package com.doconnect.repository;

import com.doconnect.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    //  Get all questions asked by a specific user
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.answers WHERE q.userId = :userId")
    List<Question> findByUserId(Long userId);

    //  Get all approved questions
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.answers WHERE q.approved = true")
    List<Question> findByApprovedTrue();

    //  Search questions by title or description
    @Query("SELECT q FROM Question q LEFT JOIN FETCH q.answers WHERE LOWER(q.title) LIKE LOWER(CONCAT('%', :title, '%')) OR LOWER(q.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<Question> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String title,
            String description
    );
}
