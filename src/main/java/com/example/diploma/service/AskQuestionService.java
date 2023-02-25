package com.example.diploma.service;

import com.example.diploma.dto.question.CreateQuestionDTORequest;
import com.example.diploma.dto.question.QuestionDTOResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

public interface AskQuestionService {
    QuestionDTOResponse createQuestion(@Validated @RequestBody CreateQuestionDTORequest question);
}
