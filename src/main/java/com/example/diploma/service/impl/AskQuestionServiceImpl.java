package com.example.diploma.service.impl;

import com.example.diploma.dao.QuestionDao;
import com.example.diploma.dto.CreateQuestionDTORequest;
import com.example.diploma.dto.QuestionDTOResponse;
import com.example.diploma.mapper.QuestionMapper;
import com.example.diploma.model.Question;
import com.example.diploma.service.AskQuestionService;
import com.example.diploma.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AskQuestionServiceImpl implements AskQuestionService {

    private final QuestionDao questionDao;
    private final QuestionRepository questionRepository;

    @Override
    public QuestionDTOResponse createQuestion(CreateQuestionDTORequest questionDto) {

        System.out.println("dsdsds"+questionDto);
        Question questionFromRepo = questionDao.findByQuestion(questionDto.getQuestion());
        Question question = new Question();
        System.out.println("dsdsdsasas"+questionFromRepo);
        if (questionFromRepo == null) {
            question = questionRepository.save(QuestionMapper.mapToQuestion(questionDto));
        }

        System.out.println("dsdsdsasas"+QuestionMapper.mapToQuestionDto(question));
        return QuestionMapper.mapToQuestionDto(question);
    }
}