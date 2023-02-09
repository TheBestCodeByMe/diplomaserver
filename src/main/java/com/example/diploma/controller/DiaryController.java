package com.example.diploma.controller;

import com.example.diploma.dto.diary.CreateDiaryDTORequest;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.service.DiaryService;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import com.example.diploma.validator.DiaryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;
    private final DiaryValidator diaryValidator;

    @PostMapping("/addAttendanceAndAcademicPerfomance")
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
            diaryDTOStreamProcessor = diaryService.addSubject(diaryDTOStreamProcessor, createDiaryDTORequest);
        }

        if (diaryDTOStreamProcessor.getResponseEntity().getStatusCode()!=HttpStatus.BAD_REQUEST) {
            return ResponseEntity.ok().body("Оценка выставлена");
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse(createDiaryDTORequest.getNamePupil()));
        }
    }
/*
    @PostMapping("/getByUserId")
    public List<DiaryDTO> getDiaryByUser(@RequestBody String userId) {
        return diaryService.getDiaryDTOByUser(Long.parseLong(userId));
    }

    @PostMapping("/getNumbAttendance")
    public String getNumberAttendance(@RequestBody String userId) {
        return String.valueOf(diaryService.getNumbAttendance(Long.parseLong(userId)));
    }

    @PostMapping("/getAverageGrade")
    public String getAvrgGrade(@RequestBody String userId) {
        double avGrade = diaryService.getAverageGrade(Long.parseLong(userId));
        if (avGrade != 0) {
            return String.valueOf(avGrade);
        } else {
            return "У ученика ещё нет оценок";
        }
    }

    @GetMapping("/getAllAboutPupil/{classForSearch}")
    public ResponseEntity<List<DiaryDTO>> getUserById(@PathVariable(value = "classForSearch") String classForSearch)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(diaryService.getDiaryDTOByClass(classForSearch));
    }

    @GetMapping("/getSaveGrades/{userId}")
    public ResponseEntity<String> getSaveDiary(@PathVariable(value = "userId") Long userId)
            throws ResourceNotFoundException {
        diaryService.saveGradesByUserId(userId);
        return ResponseEntity.ok().body("okeyy");
    }*/
}