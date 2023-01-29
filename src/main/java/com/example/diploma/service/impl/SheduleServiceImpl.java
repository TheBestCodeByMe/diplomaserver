package com.example.diploma.service.impl;


import com.example.diploma.dto.SheduleDTO;
import com.example.diploma.model.*;
import com.example.diploma.exception.ResourceNotFoundException;
import com.example.diploma.map.Mapper;
import com.example.diploma.service.SheduleService;
import com.example.diploma.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SheduleServiceImpl implements SheduleService {

    private final AttendanceRepository attendanceRepository;

    private final AcademicPerfomanceRepository academicPerfomanceRepository;

    private final PupilRepository pupilRepository;

    private final SheduleRepository sheduleRepository;

    private final SubjectRepository subjectRepository;

    private final ClassroomRepository classroomRepository;

    private final TeacherRepository teacherRepository;

    private final CalendarRepository calendarRepository;

    @Override
    public ResponseEntity<List<SheduleDTO>> getScheduleByIdAndDate(@PathVariable(value = "userId") Long userId, @PathVariable(value = "date") String date)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(getScheduleDTOByIdAndDate(userId, date));
    }

    @Override
    public List<SheduleDTO> getScheduleDTOByIdAndDate(Long userId, String date) {
        Pupil pupil = pupilRepository.findByUserId(userId);
        List<Shedule> sheduleList = sheduleRepository.findAllByClassroomIDAndDate(pupil.getClassroomId(), Date.valueOf(date));
        List<SheduleDTO> sheduleDTOList = new ArrayList<>();

        Classroom classroom = classroomRepository.getById(pupil.getClassroomId());

        for (Shedule shedule : sheduleList) {
            Subject subject = subjectRepository.getById(shedule.getSubjectID());
            Teacher teacher = teacherRepository.getById(shedule.getTeacherID());
            Calendar calendar = calendarRepository.getById(shedule.getCalendarId());

            System.out.println(subject + " " + teacher + " " + calendar);
            SheduleDTO sheduleDTO = Mapper.mapSheduleToSheduleDTO(shedule, calendar, subject, teacher, classroom);
            System.out.println(sheduleDTO);
            sheduleDTOList.add(sheduleDTO);
        }
        return sheduleDTOList;
    }

}