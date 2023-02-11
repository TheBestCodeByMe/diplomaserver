package com.example.diploma.service;

import com.example.diploma.dto.diary.CreateDiaryDTORequest;
import com.example.diploma.dto.diary.DiaryDTO;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiaryService {
    DiaryDTOStreamProcessor addAcademicPerformance(DiaryDTOStreamProcessor diaryDTOStreamProcessor, CreateDiaryDTORequest createDiaryDTORequest);

    DiaryDTOStreamProcessor addAttendance(DiaryDTOStreamProcessor diaryDTOStreamProcessor);

    DiaryDTOStreamProcessor addHomework(DiaryDTOStreamProcessor diaryDTOStreamProcessor, CreateDiaryDTORequest createDiaryDTORequest);

    DiaryDTOStreamProcessor getAttendance(DiaryDTOStreamProcessor diaryDTOStreamProcessor);

    DiaryDTOStreamProcessor getAcademicPerformance(DiaryDTOStreamProcessor diaryDTOStreamProcessor);

    List<DiaryDTO> getDiaryDTOByUser(Long id);

    int getNumbAttendance(Long id);

    ResponseEntity<?> getDiaryDTOByClass(String classForSearch);

    double getAverageGrade(Long id);

    void saveGradesByUserId(Long userId);
}
