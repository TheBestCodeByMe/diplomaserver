package com.example.diploma.dao;

import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Pupil;
import com.example.diploma.model.Teacher;
import com.example.diploma.repo.PupilRepository;
import com.example.diploma.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeacherDao {

    private final TeacherRepository teacherRepository;

    public Teacher findByFio(String classroomTeacherName, String classroomTeacherLastname, String classroomTeacherPatronymic) {
        return teacherRepository.findByNameAndLastNameAndPatronymic(classroomTeacherName, classroomTeacherLastname, classroomTeacherPatronymic);
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAllByStatusId(EStatus.ACTIVE.getId());
    }
}
