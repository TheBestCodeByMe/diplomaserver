package com.example.diploma.repo;

import com.example.diploma.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("""
            select s from Schedule s
            where s.calendarId = ?1 and s.classroomID = ?2 and s.date = ?3 and s.subjectID = ?4 and s.teacherID = ?5 and s.weekDay = ?6""")
    Schedule findSchedule(Long calendarId, Long classroomID, LocalDate date, Long subjectID, Long teacherID, int weekDay);

    Schedule findByTeacherIDAndCalendarIdAndDate(Long teacherId, Long calendarId, LocalDate date);
    Schedule findByCalendarIdAndClassroomIDAndDate(Long calendarId, Long classroomId, LocalDate date);
    Schedule findByDateAndClassroomIDAndSubjectID(LocalDate date, Long classroomId, Long subjectId);
    List<Schedule> findAllByClassroomID(Long classroomId);
    List<Schedule> findAllByClassroomIDAndDate(Long classroomId, LocalDate date);
}
