package com.example.diploma.repo;

import com.example.diploma.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByNameAndLastNameAndPatronymic(String name, String lastname, String patronymic);
    Teacher findByUserId(Long userId);
    List<Teacher> findAllByStatusId(Long statusId);
}
