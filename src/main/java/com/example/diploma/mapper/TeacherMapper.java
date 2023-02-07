package com.example.diploma.mapper;

import com.example.diploma.dto.teacher.CreateTeacherDTORequest;
import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Teacher;

import java.time.LocalDateTime;

import static java.sql.Timestamp.valueOf;

public class TeacherMapper {

    public static Teacher mapDtoToTeacher(CreateTeacherDTORequest teacherDTO, String code) {
        return new Teacher(2, teacherDTO.getName(), teacherDTO.getLastName(), teacherDTO.getPatronymic(), teacherDTO.getEmail(), teacherDTO.getQualification(), teacherDTO.getPosition(), EStatus.ACTIVE.getId(), code, LocalDateTime.now(), null);
    }

    public static TeacherDTO mapToTeacherDto(Teacher teacher) {
        return new TeacherDTO(teacher.getName(), teacher.getLastName(), teacher.getPatronymic(), teacher.getEmail(), teacher.getQualification(), teacher.getPosition(), EStatus.ACTIVE.getName(), teacher.getCode());
    }
}
