package com.example.diploma.dao;

import com.example.diploma.dto.CreateQuestionDTORequest;
import com.example.diploma.dto.QuestionDTOResponse;
import com.example.diploma.model.Question;
import com.example.diploma.repo.QuestionRepository;
import com.example.diploma.service.AskQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Component
@RequiredArgsConstructor
public class QuestionDao {

    private final QuestionRepository questionRepository;

    public Question findByQuestion(String question) {
        return questionRepository.findByQuestion(question);
    }
}
