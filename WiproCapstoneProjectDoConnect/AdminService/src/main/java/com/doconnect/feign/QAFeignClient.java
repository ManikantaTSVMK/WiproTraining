package com.doconnect.feign;

import com.doconnect.dto.QuestionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "qa-service", contextId = "questionClient")
public interface QAFeignClient {

    // Fetch all questions (Admin view)
    @GetMapping("/questions/all")
    List<QuestionDTO> getAllQuestions();

    //  Create a new question
    @PostMapping("/questions")
    QuestionDTO createQuestion(@RequestBody QuestionDTO question);

    //  Approve a question
    @PutMapping("/questions/{id}/approve")
    QuestionDTO approveQuestion(@PathVariable("id") Long id);

    //  Reject a question
    @PutMapping("/questions/{id}/reject")
    QuestionDTO rejectQuestion(@PathVariable("id") Long id);

    //  Mark a question as resolved
    @PutMapping("/questions/{id}/resolve")
    QuestionDTO resolveQuestion(@PathVariable("id") Long id);

    //  Delete a question
    @DeleteMapping("/questions/{id}")
    String deleteQuestion(@PathVariable("id") Long id);
}
