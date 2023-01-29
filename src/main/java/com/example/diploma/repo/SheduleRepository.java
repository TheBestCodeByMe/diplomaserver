package com.example.diploma.repo;

import com.example.diploma.model.Shedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SheduleRepository extends JpaRepository<Shedule, Long> {
    Shedule findByCalendarIdAndClassroomIDAndDateAndSubjectIDAndTeacherIDAndWeekDay(Long calendarId, Long classroomID, Date date, Long subjectID, Long teacherID, int weekDay);
    Shedule findByTeacherIDAndCalendarIdAndDate(Long teacherId, Long calendarId, Date date);
    Shedule findByCalendarIdAndClassroomIDAndDate(Long calendarId, Long classroomId, Date date);
    Shedule findByDateAndClassroomIDAndSubjectID(Date date, Long classroomId, Long subjectId);
    List<Shedule> findAllByClassroomID(Long classroomId);
    List<Shedule> findAllByClassroomIDAndDate(Long classroomId, Date date);
}
