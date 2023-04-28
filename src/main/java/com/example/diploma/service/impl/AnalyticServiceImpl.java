package com.example.diploma.service.impl;

import com.example.diploma.dao.*;
import com.example.diploma.dto.analytic.AcademicPerformanceInClassDTO;
import com.example.diploma.dto.analytic.AcademicPerformanceInMonthDTO;
import com.example.diploma.dto.analytic.AnalyticDTOResponse;
import com.example.diploma.dto.analytic.AttendanceInMonthDTO;
import com.example.diploma.dto.diary.*;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.mapper.DiaryMapper;
import com.example.diploma.model.*;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.repo.*;
import com.example.diploma.service.AnalyticService;
import com.example.diploma.service.DiaryService;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

@Service
@RequiredArgsConstructor
public class AnalyticServiceImpl implements AnalyticService {

    private final AttendanceDao attendanceDao;

    private final AcademicPerformanceDao academicPerformanceDao;

    private final PupilDao pupilDao;

    private final ScheduleDao scheduleDao;

    private final SubjectDao subjectDao;

    private final ClassroomDao classroomDao;

    private final DiaryService diaryService;

    // TODO: do this method
    @Override
    public AnalyticDTOResponse getAnalyticPage(Long userId, int sem) {
        AnalyticDTOResponse analyticDTOResponse = new AnalyticDTOResponse();
        List<AcademicPerformanceInClassDTO> academicPerformanceInClassDTO = new ArrayList<>();
        List<AcademicPerformanceInMonthDTO> academicPerformanceInMonthDTO = new ArrayList<>();
        List<AttendanceInMonthDTO> attendanceInMonthDTO = new ArrayList<>();

        analyticDTOResponse.setAcademicPerformanceInMonthDTOList(academicPerformanceInMonthDTO);
        analyticDTOResponse.setAcademicPerformanceInClassDTOList(academicPerformanceInClassDTO);
        analyticDTOResponse.setAttendanceInMonthDTOList(attendanceInMonthDTO);
        return analyticDTOResponse;
    }
}