package com.example.diploma.service;

import com.example.diploma.dto.diary.CreateDiaryDTORequest;
import com.example.diploma.dto.diary.DiaryBySubjectDTO;
import com.example.diploma.dto.diary.DiaryDTO;
import com.example.diploma.model.AcademicPerfomance;
import com.example.diploma.model.Attendance;
import com.example.diploma.model.Pupil;
import com.example.diploma.model.Schedule;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GradebookService {
    String addAcademicPerformance(Pupil pupil, Schedule schedule, DiaryBySubjectDTO diaryBySubjectDTO);

    String addAttendance(Pupil pupil, Schedule schedule);

    Boolean getAttend(Pupil pupil, Schedule schedule);

    Boolean getAcademicPerform(Pupil pupil, Schedule schedule);

    Attendance getAttendance(Pupil pupil, Schedule schedule);

    AcademicPerfomance getAcademicPerformance(Pupil pupil, Schedule schedule);
}
