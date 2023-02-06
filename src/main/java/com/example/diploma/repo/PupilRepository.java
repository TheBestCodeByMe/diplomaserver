package com.example.diploma.repo;

import com.example.diploma.model.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PupilRepository extends JpaRepository<Pupil, Long> {
    Pupil findByNameAndLastnameAndPatronymic(String name, String lastname, String patronymic);
    Pupil findByUserId(Long userId);
    List<Pupil> findAllByClassroomId(Long classroomId);
}
