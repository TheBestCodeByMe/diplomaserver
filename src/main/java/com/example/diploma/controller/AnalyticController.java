package com.example.diploma.controller;

import com.example.diploma.dao.SubjectDao;
import com.example.diploma.dao.TeacherDao;
import com.example.diploma.dto.diary.CreateDiaryDTORequest;
import com.example.diploma.dto.diary.DiaryBySubjectDTORequest;
import com.example.diploma.dto.diary.DiaryDTO;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.*;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.service.DiaryService;
import com.example.diploma.service.GradebookService;
import com.example.diploma.service.PupilService;
import com.example.diploma.service.ScheduleService;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import com.example.diploma.validator.DiaryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/analytic")
@RequiredArgsConstructor
public class AnalyticController {

    private final DiaryService diaryService;

    @GetMapping("/by/user/get/{userId}")
    public ResponseEntity<?> getAnalyticPageByUser(@PathVariable(value = "userId") String userId) {
        List<DiaryDTO> diaryDTOList = diaryService.getDiaryDTOByUser(Long.parseLong(userId));
        if (diaryDTOList == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Таких пользователей нет"));
        } else {
            return ResponseEntity.ok(diaryDTOList);
        }
    }
}