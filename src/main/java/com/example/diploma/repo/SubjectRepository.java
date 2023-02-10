package com.example.diploma.repo;

import com.example.diploma.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findBySubjectName(String subjectName);
    List<Subject> findAllByStatusId(Long statusId);
}
