package com.example.diploma.mapper;

import com.example.diploma.dto.*;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.*;
import com.example.diploma.service.impl.GenerationCodeServiceImpl;

import java.time.Instant;
import java.util.Date;

public class QuestionMapper {
    public static QuestionDTOResponse mapToQuestionDto(Question question) {
        return new QuestionDTOResponse(
         question.getQuestion(), question.getResponse(), question.isFlag(), EStatus.getName(question.getStatusId()), question.getCode()
        );
    }

    public static Question mapToQuestion(CreateQuestionDTORequest question) {
        return new Question (
                question.getQuestion(), true, EStatus.ACTIVE.getId(), GenerationCodeServiceImpl.generateCode(), Date.from(Instant.ofEpochSecond(System.currentTimeMillis()))
        );
    }
}
