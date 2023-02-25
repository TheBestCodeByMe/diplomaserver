package com.example.diploma.controller;

import com.example.diploma.dto.question.CreateQuestionDTORequest;
import com.example.diploma.dto.question.QuestionDTOResponse;
import com.example.diploma.service.AskQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/askQuestion")
@RequiredArgsConstructor
public class AskQuestionController {

    private final AskQuestionService askQuestionService;

    @PostMapping("/addQuestion")
    public QuestionDTOResponse createQuestion(@Validated @RequestBody CreateQuestionDTORequest question) {
        return askQuestionService.createQuestion(question);
    }
}
