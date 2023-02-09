package com.example.diploma.repo;

import com.example.diploma.model.AcademicPerfomance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicPerformanceRepository extends JpaRepository<AcademicPerfomance, Long> {
    boolean existsByClassIDAndLessonIDAndPupilIDAndStatusId(Long classId, Long lessonId, Long pupilId, Long statusId);
    AcademicPerfomance findByClassIDAndLessonIDAndPupilIDAndStatusId(Long classId, Long lessonId, Long pupilId, Long statusId);
    List<AcademicPerfomance> findAllByPupilID(Long pupilId);
}
