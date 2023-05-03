package com.example.diploma.repo;

import com.example.diploma.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByClassIDAndLessonIDAndPupilIDAndStatusId(Long classId, Long lessonId, Long pupilId, Long statusId);
    Attendance findByClassIDAndLessonIDAndPupilIDAndStatusId(Long classId, Long lessonId, Long pupilId, Long statusId);
    List<Attendance> findAllByPupilIDAndStatusId(Long pupilId, Long statusId);

    @Query("select a from Attendance a, Schedule s, Calendar c where a.pupilID = ?1 and a.statusId = ?2 and a.lessonID = s.id and s.calendarId = c.id and c.semesterID = ?3")
    List<Attendance> findAllByPupilIDStatusIdAndSem(Long pupilId, Long statusId, int sem);

    @Query("select a from Attendance a, Schedule s, Calendar c where a.classID = ?1 and a.statusId = ?2 and a.lessonID = s.id and s.calendarId = c.id and c.semesterID = ?3")
    List<Attendance> findAllByClassIDStatusIdAndSem(Long classId, Long statusId, int sem);
}
