package com.example.diploma.dao;

import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Pupil;
import com.example.diploma.model.Question;
import com.example.diploma.repo.PupilRepository;
import com.example.diploma.repo.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PupilDao {

    private final PupilRepository pupilRepository;

    public Pupil findByUserId(Long userId) {
        return pupilRepository.findByUserId(userId);
    }

    public Pupil findByFio(String name, String lastname, String patronymic) {
        return pupilRepository.findByNameAndLastnameAndPatronymic(name, lastname, patronymic);
    }

    public List<Pupil> findAllByClassroomId(Long id) {
        return pupilRepository.findAllByClassroomIdAndStatusId(id, EStatus.ACTIVE.getId());
    }
}
