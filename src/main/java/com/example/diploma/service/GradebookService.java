package com.example.diploma.service;

import com.example.diploma.dto.diary.CreateDiaryDTORequest;
import com.example.diploma.dto.diary.DiaryBySubjectDTO;
import com.example.diploma.dto.diary.DiaryDTO;
import com.example.diploma.model.Pupil;
import com.example.diploma.model.Schedule;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GradebookService {
    String addAcademicPerformance(Pupil pupil, Schedule schedule, DiaryBySubjectDTO diaryBySubjectDTO);

    String addAttendance(Pupil pupil, Schedule schedule);

    Boolean getAttendance(Pupil pupil, Schedule schedule);

    Boolean getAcademicPerformance(Pupil pupil, Schedule schedule);
}
