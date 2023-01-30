package com.example.diploma.service;

import com.example.diploma.dto.CreateQuestionDTORequest;
import com.example.diploma.dto.QuestionDTOResponse;
import com.example.diploma.model.Question;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

public interface AskQuestionService {
    QuestionDTOResponse createQuestion(@Validated @RequestBody CreateQuestionDTORequest question);
}
