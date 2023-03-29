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
    List<Schedule> findAllByClassroomIDAndStatusId(Long classroomId, Long statusId);
    List<Schedule> findAllByClassroomIDAndDate(Long classroomId, LocalDate date);

    /*select new com.example.diploma.dto.schedule.DatesDTO(s.schedule_date as date, cal.calendar_semester as semester, s.schedule_hometask as hometask, s.schedule_code as scheduleCode) from schedule s, classroom c, subject sub, teacher t, calendar cal
     */
    @Query(value = """
            select s from Schedule s, Classroom c, Subject sub, Teacher t, Calendar cal
            where c.classroomTeacherId = t.id and t.userId = ?1
            and s.subjectID = sub.id
            and sub.code = ?2
            and c.id = s.classroomID
            and c.name = ?3
            and s.statusId = ?4
            and s.calendarId = cal.id
            and cal.semesterID = ?5""")
    List<Schedule> findDatesDto(Long userId, String subjectCode, String classroomName, Long statusId, int semesterId);
}
