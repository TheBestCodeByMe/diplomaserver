package com.example.diploma.controller;

import com.example.diploma.model.Question;
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
    public Question createQuestion(@Validated @RequestBody Question question) {
        return askQuestionService.createQuestion(question);
    }
}
