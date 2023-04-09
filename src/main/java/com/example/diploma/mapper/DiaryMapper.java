package com.example.diploma.mapper;

import com.example.diploma.dto.classroom.ClassroomDTO;
import com.example.diploma.dto.classroom.ClassroomDTOSearch;
import com.example.diploma.dto.diary.DiaryBySubjectDTO;
import com.example.diploma.dto.diary.DiaryDTO;
import com.example.diploma.dto.pupil.CreatePupilDTORequest;
import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.*;

import java.time.LocalDateTime;

public class DiaryMapper {
    public static DiaryDTO mapToDiaryDTO(Schedule schedule, Pupil pupil, Classroom classroom, boolean attendance, String grade, Subject subject) {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setNamePupil(pupil.getName());
        diaryDTO.setLastnamePupil(pupil.getLastname());
        diaryDTO.setPatronymicPupil(pupil.getPatronymic());
        diaryDTO.setSubject(subject.getSubjectName());
        diaryDTO.setHomework(schedule.getHometask());
        diaryDTO.setGrade(grade);
        diaryDTO.setAttendance(attendance);
        diaryDTO.setDateLesson(schedule.getDate());
        diaryDTO.setClassName(classroom.getName());

        return diaryDTO;
    }

    public static DiaryBySubjectDTO mapToDiaryBySubjectDTO(Schedule schedule, boolean attendance, int grade) {
        DiaryBySubjectDTO diaryDTO = new DiaryBySubjectDTO();
        diaryDTO.setGrade(grade);
        diaryDTO.setAttendance(attendance);
        diaryDTO.setDateLesson(schedule.getDate());
        diaryDTO.setScheduleCode(schedule.getCode());

        return diaryDTO;
    }
}
