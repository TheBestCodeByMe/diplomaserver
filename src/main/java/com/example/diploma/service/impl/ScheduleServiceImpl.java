package com.example.diploma.service.impl;


import com.example.diploma.dao.*;
import com.example.diploma.dto.schedule.ScheduleDTO;
import com.example.diploma.mapper.ScheduleMapper;
import com.example.diploma.model.*;
import com.example.diploma.service.ScheduleService;
import com.example.diploma.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final AttendanceRepository attendanceRepository;

    private final AcademicPerformanceRepository academicPerfomanceRepository;

    private final PupilRepository pupilRepository;
    private final PupilDao pupilDao;

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDao scheduleDao;

    private final SubjectRepository subjectRepository;
    private final SubjectDao subjectDao;

    private final ClassroomRepository classroomRepository;
    private final ClassroomDao classroomDao;

    private final TeacherRepository teacherRepository;
    private final TeacherDao teacherDao;

    private final CalendarRepository calendarRepository;
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
}