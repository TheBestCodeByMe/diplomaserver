package com.example.diploma.mapper;

import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Teacher;

import java.time.LocalDateTime;

import static java.sql.Timestamp.valueOf;

public class TeacherMapper {

    public static Teacher mapDtoToTeacher(TeacherDTO teacherDTO, String code) {
        return new Teacher(teacherDTO.getName(), teacherDTO.getLastName(), teacherDTO.getPatronymic(), teacherDTO.getEmail(), teacherDTO.getQualification(), teacherDTO.getPosition(), EStatus.ACTIVE.getId(), code, valueOf(LocalDateTime.now()));
    }

    public static TeacherDTO mapToTeacherDto(Teacher teacher) {
        return new TeacherDTO(teacher.getName(), teacher.getLastName(), teacher.getPatronymic(), teacher.getEmail(), teacher.getQualification(), teacher.getPosition(), EStatus.ACTIVE.getName(), teacher.getCode());
    }
}
