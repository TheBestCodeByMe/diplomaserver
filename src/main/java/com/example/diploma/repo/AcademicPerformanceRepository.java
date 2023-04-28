package com.example.diploma.repo;

import com.example.diploma.model.AcademicPerfomance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcademicPerformanceRepository extends JpaRepository<AcademicPerfomance, Long> {
    boolean existsByClassIDAndLessonIDAndPupilIDAndStatusId(Long classId, Long lessonId, Long pupilId, Long statusId);
    AcademicPerfomance findByClassIDAndLessonIDAndPupilIDAndStatusId(Long classId, Long lessonId, Long pupilId, Long statusId);
    List<AcademicPerfomance> findAllByPupilIDAndStatusId(Long pupilId, Long statusId);

    @Query("select a from AcademicPerfomance a, Schedule s, Calendar c where a.pupilID = ?1 and a.statusId = ?2 and a.classID = ?3 and a.lessonID = s.id and s.calendarId = c.id and c.semesterID = ?4")
    List<AcademicPerfomance> findAllByPupilStatusClassSem(Long pupilId, Long statusId, Long classId, int sem);
}
