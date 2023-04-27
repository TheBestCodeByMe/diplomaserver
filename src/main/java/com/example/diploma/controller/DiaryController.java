package com.example.diploma.controller;

import com.example.diploma.dao.SubjectDao;
import com.example.diploma.dao.TeacherDao;
import com.example.diploma.dto.diary.CreateDiaryDTORequest;
import com.example.diploma.dto.diary.DiaryBySubjectDTORequest;
import com.example.diploma.dto.diary.DiaryDTO;
import com.example.diploma.model.Pupil;
import com.example.diploma.model.Schedule;
import com.example.diploma.model.Subject;
import com.example.diploma.model.Teacher;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.service.DiaryService;
import com.example.diploma.service.GradebookService;
import com.example.diploma.service.PupilService;
import com.example.diploma.service.ScheduleService;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import com.example.diploma.validator.DiaryValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;
    private final PupilService pupilService;
    private final GradebookService gradebookService;
    private final DiaryValidator diaryValidator;
    private final ScheduleService scheduleService;
    private final SubjectDao subjectDao;
    private final TeacherDao teacherDao;

    // TODO: Optimize and take all with one request
    @PostMapping("/saveGradebooks/{userId}")
    public ResponseEntity<?> addGradebooks(@RequestBody DiaryBySubjectDTORequest diariesBySubjectRequest, @PathVariable(value = "userId") Long userId) {
        List<Pupil> pupilList = pupilService.getPupilsByClassname(diariesBySubjectRequest.getClassname());
        Subject subject = subjectDao.findBySubjectName(diariesBySubjectRequest.getSubject());
        List<Schedule> scheduleList = scheduleService.getSchedulesBySubjectAndClass(subject.getCode(), diariesBySubjectRequest.getClassname(), userId, 1);
        List<String> result = new ArrayList<>();

        diariesBySubjectRequest.getDiaries().forEach(pupilDto -> {
            Pupil pupil = pupilList.stream().filter(it -> it.getCode().equals(pupilDto.getPupilCode())).findFirst().get();
            pupilDto.getDiary().forEach(diary -> {
                Schedule schedule = scheduleList.stream().filter(it -> it.getCode().equals(diary.getScheduleCode())).findFirst().get();
                if (diary.isAttendance()) {
                    if (!gradebookService.getAcademicPerformance(pupil, schedule)) {
                        result.add(gradebookService.addAttendance(pupil, schedule));
                    } else {
                        result.add("Error: Оценка у ученика " + pupil.getName() + " " + pupil.getLastname() + " уже выставлена, то есть он был в этот день");
                    }
                } else if (diary.getGrade() != 0) {
                    System.out.println(diary.getGrade());
                    if (!gradebookService.getAttendance(pupil, schedule)) {
                        System.out.println(gradebookService.getAttendance(pupil, schedule));
                        result.add(gradebookService.addAcademicPerformance(pupil, schedule, diary));
                    } else {
                        result.add("Error: Этого ученика " + pupil.getName() + " " + pupil.getLastname() + " не было в этот день");
                    }
                }
            });
        });

        if (result.stream().anyMatch(it -> it.contains("Error"))) {
            return ResponseEntity.badRequest().body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

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

    // if flag==true param = date else param = subj
    @GetMapping("/by/user-date-subject/get/{userId}/{param}/{flag}/{sem}")
    public ResponseEntity<?> getDiaryByUserAndDate(@PathVariable(value = "userId") String userId, @PathVariable(value = "param") String param, @PathVariable(value = "flag") boolean flag, @PathVariable(value = "sem") int semesterId) {
        List<DiaryDTO> diaryDTOList = diaryService.getDiaryDTOByParam(Long.parseLong(userId), param, semesterId, flag);
        if (diaryDTOList == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Таких пользователей нет"));
        } else {
            return ResponseEntity.ok(diaryDTOList);
        }
    }

    @GetMapping("/getNumbAttendance/{userId}")
    public ResponseEntity<?> getNumberAttendance(@PathVariable(value = "userId") String userId) {
        return ResponseEntity.ok(diaryService.getNumbAttendance(Long.parseLong(userId), false));
    }

    @GetMapping("/getAverageGrade/{userId}")
    public ResponseEntity<?> getAvrgGrade(@PathVariable(value = "userId") String userId) {
        double avGrade = diaryService.getAverageGrade(Long.parseLong(userId), false, 0L);
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