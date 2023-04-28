package com.example.diploma.service;

import com.example.diploma.dto.analytic.AnalyticDTOResponse;
import com.example.diploma.dto.diary.CreateDiaryDTORequest;
import com.example.diploma.dto.diary.DiaryDTO;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.AcademicPerfomance;
import com.example.diploma.model.Attendance;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnalyticService {
    AnalyticDTOResponse getAnalyticPage(Long id, int sem);
}
