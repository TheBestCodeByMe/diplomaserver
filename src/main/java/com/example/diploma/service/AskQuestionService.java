package com.example.diploma.service;

import com.example.diploma.model.Question;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

public interface AskQuestionService {
    Question createQuestion(@Validated @RequestBody Question question);
}
