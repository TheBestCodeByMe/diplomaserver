package com.example.diploma.validator;

import com.example.diploma.dao.ClassroomDao;
import com.example.diploma.dao.PupilDao;
import com.example.diploma.dao.ScheduleDao;
import com.example.diploma.dao.SubjectDao;
import com.example.diploma.dto.diary.CreateDiaryDTORequest;
import com.example.diploma.model.*;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiaryValidator {

    private final PupilDao pupilDao;

    private final ScheduleDao scheduleDao;

    private final SubjectDao subjectDao;

    private final ClassroomDao classroomDao;

    public DiaryDTOStreamProcessor validate(CreateDiaryDTORequest createDiaryDTORequest) {
        DiaryDTOStreamProcessor diaryDTOStreamProcessor = new DiaryDTOStreamProcessor();
        Pupil pupil = new Pupil();
        Classroom classroom = new Classroom();
        Schedule schedule;
        Subject subject = subjectDao.findBySubjectName(createDiaryDTORequest.getSubject());
        diaryDTOStreamProcessor.setResponseEntity(new ResponseEntity<>("Not error: Ок", HttpStatus.OK));
        if(createDiaryDTORequest.getClassName().equals("")){
            pupil = pupilDao.findByFio(createDiaryDTORequest.getNamePupil(), createDiaryDTORequest.getLastnamePupil(), createDiaryDTORequest.getPatronymicPupil());
        } else {
            classroom = classroomDao.findClassroomByName(createDiaryDTORequest.getClassName());
        }

        if (pupil == null) {
            diaryDTOStreamProcessor.setResponseEntity(new ResponseEntity<>("Error: Ошибка в ФИО ученика", HttpStatus.BAD_REQUEST));
            diaryDTOStreamProcessor.setAcademicPerform(false);
            return diaryDTOStreamProcessor;
        } else if (subject == null) {
            diaryDTOStreamProcessor.setResponseEntity(new ResponseEntity<>("Error: Ошибка в названии предмета", HttpStatus.BAD_REQUEST));
            diaryDTOStreamProcessor.setAcademicPerform(false);
            return diaryDTOStreamProcessor;
        }

        if(createDiaryDTORequest.getClassName().equals("")) {
            schedule = scheduleDao.findByDateAndClassroomIDAndSubjectID(createDiaryDTORequest.getDateLesson(), pupil.getClassroomId(), subject.getId());
        } else {
            schedule = scheduleDao.findByDateAndClassroomIDAndSubjectID(createDiaryDTORequest.getDateLesson(), classroom.getId(), subject.getId());
        }

        if (schedule == null) {
            diaryDTOStreamProcessor.setResponseEntity(new ResponseEntity<>("Error: Ошибка в дате занятия", HttpStatus.BAD_REQUEST));
            diaryDTOStreamProcessor.setAcademicPerform(false);
            return diaryDTOStreamProcessor;
        }
        diaryDTOStreamProcessor.setSchedule(schedule);
        diaryDTOStreamProcessor.setPupil(pupil);
        diaryDTOStreamProcessor.setSubject(subject);
        diaryDTOStreamProcessor.setClassroom(classroom);

        return diaryDTOStreamProcessor;
    }
}