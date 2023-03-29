package com.example.diploma.service.impl;


import com.example.diploma.dao.*;
import com.example.diploma.dto.schedule.ScheduleDatesDTO;
import com.example.diploma.dto.schedule.ScheduleDTO;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.mapper.ScheduleMapper;
import com.example.diploma.model.*;
import com.example.diploma.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final PupilDao pupilDao;

    private final ScheduleDao scheduleDao;

    private final SubjectDao subjectDao;

    private final ClassroomDao classroomDao;

    private final TeacherDao teacherDao;

    private final CalendarDao calendarDao;

    @Override
    public List<ScheduleDTO> getScheduleDTOByIdAndDate(Long userId, String date) {
        Pupil pupil = pupilDao.findByUserId(userId);
        List<Schedule> scheduleList = scheduleDao.findAllByClassroomIDAndDate(pupil.getClassroomId(), Date.valueOf(date).toLocalDate());
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        Classroom classroom = classroomDao.findByClassroomId(pupil.getClassroomId());

        for (Schedule schedule : scheduleList) {
            Subject subject = subjectDao.getById(schedule.getSubjectID());
            Teacher teacher = teacherDao.getById(schedule.getTeacherID());
            Calendar calendar = calendarDao.getById(schedule.getCalendarId());

            ScheduleDTO scheduleDTO = ScheduleMapper.mapScheduleToScheduleDTO(schedule, calendar, subject, teacher, classroom);
            scheduleDTOList.add(scheduleDTO);
        }
        return scheduleDTOList;
    }

    @Override
    public List<ScheduleDatesDTO> getDatesBySubjectAndClass(String subject, String classname, Long userId, int semesterId) {
        List<Schedule> scheduleList = scheduleDao.findByClassAndSubjectAndTeacher(userId, classname, subject, EStatus.ACTIVE, semesterId);
        List<ScheduleDatesDTO> scheduleDatesDTOList = new ArrayList<>();

        scheduleList.forEach(schedule -> { scheduleDatesDTOList.add(ScheduleMapper.mapScheduleToDatesDTO(schedule, semesterId)); });
        return scheduleDatesDTOList;
    }
}