package com.example.diploma.repo;

import com.example.diploma.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByClassIDAndLessonIDAndPupilIDAndStatusId(Long classId, Long lessonId, Long pupilId, Long statusId);
    Attendance findByClassIDAndLessonIDAndPupilIDAndStatusId(Long classId, Long lessonId, Long pupilId, Long statusId);
    List<Attendance> findAllByPupilID(Long pupilId);
}
