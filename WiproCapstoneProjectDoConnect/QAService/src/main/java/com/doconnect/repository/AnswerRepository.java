package com.doconnect.repository;

import com.doconnect.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> { // Repository for Answer entity with CRUD operations
}
