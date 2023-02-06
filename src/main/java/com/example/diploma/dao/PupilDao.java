package com.example.diploma.dao;

import com.example.diploma.model.Pupil;
import com.example.diploma.model.Question;
import com.example.diploma.repo.PupilRepository;
import com.example.diploma.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PupilDao {

    private final PupilRepository pupilRepository;

    public Pupil findByUserId(Long userId) {
        return pupilRepository.findByUserId(userId);
    }
}
