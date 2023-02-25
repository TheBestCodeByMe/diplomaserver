package com.example.diploma.dao;

import com.example.diploma.model.Question;
import com.example.diploma.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionDao {

    private final QuestionRepository questionRepository;

    public Question findByQuestion(String question) {
        return questionRepository.findByQuestion(question);
    }
}
