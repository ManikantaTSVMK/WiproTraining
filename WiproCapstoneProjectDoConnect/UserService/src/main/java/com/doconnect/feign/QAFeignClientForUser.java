package com.doconnect.feign;

import com.doconnect.dto.AnswerDTO;
import com.doconnect.dto.QuestionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "qa-service", contextId = "qaForUser")
public interface QAFeignClientForUser {

    //  Fetch all questions asked by a specific user
    @GetMapping("/questions/user/{userId}")
    List<QuestionDTO> getQuestionsByUser(@PathVariable("userId") Long userId);

    //  Ask a new question
    @PostMapping("/questions")
    QuestionDTO askQuestion(@RequestBody QuestionDTO question);

    //  Fetch all approved questions
    @GetMapping("/questions")
    List<QuestionDTO> getAllApprovedQuestions();

    //  User gives an answer
    @PostMapping("/answers/{questionId}")
    AnswerDTO giveAnswer(@PathVariable("questionId") Long questionId,
                         @RequestBody AnswerDTO answer);

    //  Like an answer
    @PutMapping("/answers/{id}/like")
    AnswerDTO likeAnswer(@PathVariable("id") Long id);

    //  Add a comment to an answer
    @PostMapping("/answers/{id}/comment")
    AnswerDTO addComment(@PathVariable("id") Long id,
                         @RequestParam("comment") String comment);

    //  Search questions
    @GetMapping("/questions/search")
    List<QuestionDTO> searchQuestions(@RequestParam("keyword") String keyword);
}
