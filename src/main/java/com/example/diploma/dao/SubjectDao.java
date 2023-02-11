package com.example.diploma.dao;

import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Classroom;
import com.example.diploma.model.Subject;
import com.example.diploma.repo.ClassroomRepository;
import com.example.diploma.repo.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubjectDao {

    private final SubjectRepository subjectRepository;

    public Subject findBySubjectName(String subjectName) {
        return subjectRepository.findBySubjectName(subjectName);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAllByStatusId(EStatus.ACTIVE.getId());
    }

    public Subject getById(Long id) {return subjectRepository.getById(id);}
}
