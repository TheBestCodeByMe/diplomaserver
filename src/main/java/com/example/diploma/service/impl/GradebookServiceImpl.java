package com.example.diploma.service.impl;

import com.example.diploma.dao.*;
import com.example.diploma.dto.diary.*;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.mapper.DiaryMapper;
import com.example.diploma.model.*;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.repo.*;
import com.example.diploma.service.DiaryService;
import com.example.diploma.service.GradebookService;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.example.diploma.enumiration.EStatus.ACTIVE;
import static com.example.diploma.enumiration.EStatus.CLOSED;

@Service
@RequiredArgsConstructor
public class GradebookServiceImpl implements GradebookService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceDao attendanceDao;

    private final AcademicPerformanceRepository academicPerformanceRepository;
    private final AcademicPerformanceDao academicPerformanceDao;

    @Override
    public String addAcademicPerformance(Pupil pupil, Schedule schedule, DiaryBySubjectDTO diaryBySubjectDTO) {
        AcademicPerfomance academicPerfomance = new AcademicPerfomance();

        if (!academicPerformanceDao.isExist(pupil.getClassroomId(), schedule.getId(), pupil.getId())) {
            academicPerfomance.setClassID(pupil.getClassroomId());
            academicPerfomance.setLessonID(schedule.getId());
            academicPerfomance.setPupilID(pupil.getId());
            academicPerfomance.setGrade(diaryBySubjectDTO.getGrade());
            academicPerfomance.setCreateDate(LocalDateTime.now());
            academicPerfomance.setCloseDate(null);
            academicPerfomance.setCode(GenerationCodeServiceImpl.generateCode());
            academicPerfomance.setStatusId(ACTIVE.getId());
            academicPerformanceRepository.save(academicPerfomance);
            return "Оценка выставлена";
        } else {
            academicPerfomance = academicPerformanceDao.find(pupil.getClassroomId(), schedule.getId(), pupil.getId());
            if (academicPerfomance.getGrade() != diaryBySubjectDTO.getGrade()) {
                academicPerfomance.setGrade(diaryBySubjectDTO.getGrade());
                academicPerformanceRepository.save(academicPerfomance);
                return "Оценка обновлена";
            }
            return "";
        }
    }

    @Override
    public String addAttendance(Pupil pupil, Schedule schedule) {
        Attendance attendance = new Attendance();

        if (!attendanceDao.isExist(pupil.getClassroomId(), schedule.getId(), pupil.getId())) {
            attendance.setPupilID(pupil.getId());
            attendance.setClassID(pupil.getClassroomId());
            attendance.setLessonID(schedule.getId());
            attendance.setCreateDate(LocalDateTime.now());
            attendance.setCloseDate(null);
            attendance.setCode(GenerationCodeServiceImpl.generateCode());
            attendance.setStatusId(ACTIVE.getId());
            attendanceRepository.save(attendance);
            return "Посещаемость выставлена";
        } else {
            attendance = attendanceDao.find(pupil.getClassroomId(), schedule.getId(), pupil.getId());
            attendance.setStatusId(CLOSED.getId());
            attendanceRepository.save(attendance);
            return "Посещаемость обновлена";
        }
    }

    @Override
    public Boolean getAttendance(Pupil pupil, Schedule schedule) {
        return attendanceDao.isExist(pupil.getClassroomId(), schedule.getId(), pupil.getId());
    }

    @Override
    public Boolean getAcademicPerformance(Pupil pupil, Schedule schedule) {
        return academicPerformanceDao.isExist(pupil.getClassroomId(), schedule.getId(), pupil.getId());
    }
}