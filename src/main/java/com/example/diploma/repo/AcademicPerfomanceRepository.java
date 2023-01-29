package com.example.diploma.repo;

import com.example.diploma.model.AcademicPerfomance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicPerfomanceRepository extends JpaRepository<AcademicPerfomance, Long> {
    boolean existsByClassIDAndLessonIDAndPupilID(Long classId, Long lessonId, Long pupilId);
    List<AcademicPerfomance> findAllByPupilID(Long pupilId);
}
