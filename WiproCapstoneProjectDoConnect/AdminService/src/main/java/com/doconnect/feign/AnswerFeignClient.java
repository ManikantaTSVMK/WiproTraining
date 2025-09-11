package com.doconnect.feign;

import com.doconnect.dto.AnswerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "qa-service", path = "/answers", contextId = "answerClient") // Feign client for communicating with QA service (answers)
public interface AnswerFeignClient {

    // Fetch all answers (Admin view)
    @GetMapping // Maps to GET /answers
    List<AnswerDTO> getAllAnswers();

    // Create a new answer
    @PostMapping // Maps to POST /answers
    AnswerDTO createAnswer(@RequestBody AnswerDTO answer);

    // Approve an answer
    @PutMapping("/{id}/approve") // Maps to PUT /answers/{id}/approve
    AnswerDTO approveAnswer(@PathVariable("id") Long id);

    // Reject an answer
    @PutMapping("/{id}/reject") // Maps to PUT /answers/{id}/reject
    AnswerDTO rejectAnswer(@PathVariable("id") Long id);

    // Delete an answer
    @DeleteMapping("/{id}") // Maps to DELETE /answers/{id}
    String deleteAnswer(@PathVariable("id") Long id);
}
