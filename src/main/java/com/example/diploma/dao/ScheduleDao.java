package com.example.diploma.dao;

import com.example.diploma.model.Calendar;
import com.example.diploma.model.Shedule;
import com.example.diploma.repo.CalendarRepository;
import com.example.diploma.repo.SheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ScheduleDao {

    private final SheduleRepository scheduleRepository;

    public Shedule findSchedule(Long calendarId, Long classroomID, Date date, Long subjectID, Long teacherID, Integer weekDay) {
        return scheduleRepository.findByCalendarIdAndClassroomIDAndDateAndSubjectIDAndTeacherIDAndWeekDay(calendarId, classroomID, date, subjectID, teacherID, weekDay);
    }

    public Shedule findForTeacher(Long teacherID, Long calendarId, Date date) {
        return scheduleRepository.findByTeacherIDAndCalendarIdAndDate(teacherID, calendarId, date);
    }

    public Shedule findForClassroom(Long calendarId, Long classroomID, Date date) {
        return scheduleRepository.findByCalendarIdAndClassroomIDAndDate(calendarId, classroomID, date);
    }
}
