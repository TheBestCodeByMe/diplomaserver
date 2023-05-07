package com.example.diploma.dao;

import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Schedule;
import com.example.diploma.repo.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleDao {

    private final ScheduleRepository scheduleRepository;

    public Schedule findSchedule(Long calendarId, Long classroomID, LocalDate date, Long subjectID, Long teacherID, Integer weekDay) {
        return scheduleRepository.findSchedule(calendarId, classroomID, date, subjectID, teacherID, weekDay);
    }

    public Schedule findForTeacher(Long teacherID, Long calendarId, LocalDate date) {
        return scheduleRepository.findByTeacherIDAndCalendarIdAndDate(teacherID, calendarId, date);
    }

    public Schedule findForClassroom(Long calendarId, Long classroomID, LocalDate date) {
        return scheduleRepository.findByCalendarIdAndClassroomIDAndDate(calendarId, classroomID, date);
    }

    public Schedule findByDateAndClassroomIDAndSubjectID(LocalDate dateLesson, Long classroomId, Long subjectId) {
        return scheduleRepository.findByDateAndClassroomIDAndSubjectID(dateLesson, classroomId, subjectId);
    }

    public List<Schedule> findAllByClassroomID(Long classroomId) {
        return scheduleRepository.findAllByClassroomIDAndStatusId(classroomId, EStatus.ACTIVE.getId());
    }

    public List<Schedule> findAllByClassSemSubj(Long classroomId, int sem, Long subjectId) {
        return scheduleRepository.findByClassSemSubj(subjectId, classroomId, EStatus.ACTIVE.getId(), sem);
    }

    public List<Schedule> findAllByClassSemSubjTeacher(Long classroomId, int sem, Long subjectId, Long teacherId) {
        return scheduleRepository.findByClassSemSubjTeacher(subjectId, classroomId, EStatus.ACTIVE.getId(), sem, teacherId);
    }

    public List<Schedule> findAllByClassroomIDAndDate(Long classroomId, LocalDate date) {
        return scheduleRepository.findAllByClassroomIDAndDate(classroomId, date);
    }

    public List<Schedule> findAllByClassDateSem(Long classroomId, LocalDate date, int sem) {
        return scheduleRepository. findAllByClassDateSem(date, classroomId, EStatus.ACTIVE.getId(), sem);
    }

    public List<Schedule> findByClassAndSubjectAndTeacher(Long userId, String classroomName, String subjectCode, EStatus status, int semesterId) {
        return scheduleRepository.findDatesDto(userId, subjectCode, classroomName, status.getId(), semesterId);
    }
}
