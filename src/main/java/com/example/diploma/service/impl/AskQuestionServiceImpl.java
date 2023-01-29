package com.example.diploma.service.impl;

import com.example.diploma.model.Question;
import com.example.diploma.service.AskQuestionService;
import com.example.diploma.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AskQuestionServiceImpl implements AskQuestionService {

    private final QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        System.out.println(question);
        Question questionFromRepo = questionRepository.findByQuestion(question.getQuestion());
        if (questionFromRepo == null) {
            questionRepository.save(question);
        }
        return question;
    }
}