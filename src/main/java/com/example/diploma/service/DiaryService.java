package com.example.diploma.service;

import com.example.diploma.dto.diary.CreateDiaryDTORequest;
import com.example.diploma.dto.diary.DiaryBySubjectDTO;
import com.example.diploma.dto.diary.DiaryBySubjectDTOResponse;
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

    int getNumbAttendance(Long id, Boolean flag);

    int getSemesterGrade(double grade);

    ResponseEntity<?> getDiaryDTOByClass(String classForSearch);

    double getAverageGrade(Long id, Boolean flag);

    void saveGradesByUserId(Long userId);

    ResponseEntity<?> getDiariesByClassAndSubject(String classname, String subject);
}
