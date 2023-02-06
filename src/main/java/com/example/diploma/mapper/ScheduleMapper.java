package com.example.diploma.mapper;

import com.example.diploma.dto.schedule.CreateScheduleDTORequest;
import com.example.diploma.dto.schedule.ScheduleDTO;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.*;

import java.time.LocalDateTime;

import static java.sql.Timestamp.valueOf;

public class ScheduleMapper {

    public static ScheduleDTO mapScheduleToScheduleDTO(Shedule schedule, Calendar calendar, Subject subject, Teacher teacher, Classroom classroom) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setHometask(schedule.getHometask());
        scheduleDTO.setClassroomName(classroom.getName());
        scheduleDTO.setLastnameTeacher(teacher.getLastName());
        scheduleDTO.setNameTeacher(teacher.getName());
        scheduleDTO.setPatronymicTeacher(teacher.getPatronymic());
        scheduleDTO.setLessonNumber(calendar.getLessonNumber());
        scheduleDTO.setSemesterId(calendar.getSemesterID());
        scheduleDTO.setSubjectName(subject.getSubjectName());
        scheduleDTO.setScheduleCode(schedule.getCode());
        scheduleDTO.setScheduleStatus(EStatus.getName(schedule.getStatusId()));

        return scheduleDTO;
    }

    public static Shedule mapScheduleDTOToSchedule(CreateScheduleDTORequest scheduleDTO, long calendarId, long teacherId, long subjectId, long classroomId, String scheduleCode) {
        Shedule schedule = new Shedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setHometask(scheduleDTO.getHometask());
        schedule.setWeekDay(scheduleDTO.getWeekDay());
        schedule.setCalendarId(calendarId);
        schedule.setSubjectID(subjectId);
        schedule.setTeacherID(teacherId);
        schedule.setClassroomID(classroomId);
        schedule.setCreateDate(valueOf(LocalDateTime.now()));
        schedule.setStatusId(EStatus.ACTIVE.getId());
        schedule.setCode(scheduleCode);
        schedule.setCloseDate(null);

        return schedule;
    }
}
