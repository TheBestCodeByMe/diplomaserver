package com.example.diploma.controller;

import com.example.diploma.dto.diary.CreateDiaryDTORequest;
import com.example.diploma.dto.diary.DiaryDTO;
import com.example.diploma.exception.ResourceNotFoundException;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.service.DiaryService;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import com.example.diploma.validator.DiaryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;
    private final DiaryValidator diaryValidator;

    @PostMapping("/addAttendanceAndAcademicPerformance")
    public ResponseEntity<?> addAttendanceAndAcademicPerformance(@RequestBody CreateDiaryDTORequest createDiaryDTORequest) {
        DiaryDTOStreamProcessor diaryDTOStreamProcessor = diaryValidator.validate(createDiaryDTORequest);

        if (diaryDTOStreamProcessor.getResponseEntity().getStatusCode() == HttpStatus.BAD_REQUEST) {
            return diaryDTOStreamProcessor.getResponseEntity();
        }

        if (createDiaryDTORequest.isAttendance()) {
            diaryDTOStreamProcessor = diaryService.getAcademicPerformance(diaryDTOStreamProcessor);
            if (!diaryDTOStreamProcessor.isAcademicPerformance()) {
                diaryDTOStreamProcessor = diaryService.addAttendance(diaryDTOStreamProcessor);
            } else {
                diaryDTOStreamProcessor.setResponseEntity(new ResponseEntity<>("Error: Оценка у ученика уже выставлена, то есть он был в этот день", HttpStatus.BAD_REQUEST));
                return diaryDTOStreamProcessor.getResponseEntity();
            }
        } else if (createDiaryDTORequest.getClassName().equals("")) {
            diaryDTOStreamProcessor = diaryService.getAttendance(diaryDTOStreamProcessor);
            if (!diaryDTOStreamProcessor.isAttendance()) {
                diaryDTOStreamProcessor = diaryService.addAcademicPerformance(diaryDTOStreamProcessor, createDiaryDTORequest);
            } else {
                diaryDTOStreamProcessor.setResponseEntity(new ResponseEntity<>("Error: Этого ученика не было в этот день", HttpStatus.BAD_REQUEST));
                return diaryDTOStreamProcessor.getResponseEntity();
            }
        } else {
            diaryDTOStreamProcessor = diaryService.addHomework(diaryDTOStreamProcessor, createDiaryDTORequest);
        }

        if (diaryDTOStreamProcessor.getResponseEntity().getStatusCode() != HttpStatus.BAD_REQUEST) {
            return ResponseEntity.ok("Выставлено");
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse(diaryDTOStreamProcessor.getResponseEntity().toString()));
        }
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getDiaryByUser(@PathVariable(value = "userId") String userId) {
        List<DiaryDTO> diaryDTOList = diaryService.getDiaryDTOByUser(Long.parseLong(userId));
        if (diaryDTOList == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Таких пользователей нет"));
        } else {
            return ResponseEntity.ok(diaryDTOList);
        }
    }

    @GetMapping("/getNumbAttendance/{userId}")
    public ResponseEntity<?> getNumberAttendance(@PathVariable(value = "userId") String userId) {
        return ResponseEntity.ok(diaryService.getNumbAttendance(Long.parseLong(userId)));
    }

    @GetMapping("/getAverageGrade/{userId}")
    public ResponseEntity<?> getAvrgGrade(@PathVariable(value = "userId") String userId) {
        double avGrade = diaryService.getAverageGrade(Long.parseLong(userId));
        if (avGrade != 0) {
            return ResponseEntity.ok(avGrade);
        } else {
            return ResponseEntity.ok("У ученика ещё нет оценок");
        }
    }

    @GetMapping("/getAllAboutPupil/{classForSearch}")
    public ResponseEntity<?> getDiaryDTOByClass(@PathVariable(value = "classForSearch") String classForSearch) {
        return diaryService.getDiaryDTOByClass(classForSearch);
    }

    @GetMapping("/getSaveGrades/{userId}")
    public ResponseEntity<?> getSaveDiary(@PathVariable(value = "userId") Long userId) {
        diaryService.saveGradesByUserId(userId);
        return ResponseEntity.ok("Сохранено в папку resources!");
    }

    @GetMapping("/getDiaries/{classname}/{subject}")
    public ResponseEntity<?> getDiaryByClassAndSubject(@PathVariable(value = "classname") String classname, @PathVariable(value = "subject") String subject) {
        return diaryService.getDiariesByClassAndSubject(classname, subject);
    }
}