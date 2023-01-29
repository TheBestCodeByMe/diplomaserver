package com.example.diploma.repo;

import com.example.diploma.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByClassIDAndLessonIDAndPupilID(Long classId, Long lessonId, Long pupilId);
    List<Attendance> findAllByPupilID(Long pupilId);
}
