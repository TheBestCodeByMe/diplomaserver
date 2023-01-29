package com.example.diploma.controller;

import com.example.diploma.dto.DiaryDTO;
import com.example.diploma.exception.ResourceNotFoundException;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/addAttendanceAndAcademicPerfomance")
    public ResponseEntity<?> addAttendanceAndAcademicPerfomance(@RequestBody DiaryDTO diaryDTO) {
        if (diaryDTO.isAttendance()) {
            if (!diaryService.getAcademicPerfomance(diaryDTO)) {
                diaryDTO = diaryService.addAttendance(diaryDTO);
            } else {
                diaryDTO.setNamePupil("Оценка у ученика уже выставлена, то есть он был в этот день");
            }
        } else if (diaryDTO.getClassName().equals("")) {
            if (!diaryService.getAttendance(diaryDTO)) {
                diaryDTO = diaryService.addAcademicPerfomance(diaryDTO);
            } else {
                diaryDTO.setNamePupil("Этого ученика не было в этот день");
            }
        } else {
            diaryDTO = diaryService.addSubject(diaryDTO);
        }

        if (diaryDTO.getNamePupil().equals("ок")) {
            return ResponseEntity.ok().body("Оценка выставлена");
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse(diaryDTO.getNamePupil()));
        }
    }

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
    }
}