package com.example.diploma.mapper;

import com.example.diploma.dto.classroom.ClassroomDTO;
import com.example.diploma.dto.classroom.ClassroomDTOSearch;
import com.example.diploma.dto.pupil.CreatePupilDTORequest;
import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Classroom;
import com.example.diploma.model.Teacher;

import java.time.LocalDateTime;

import static java.sql.Timestamp.valueOf;

public class ClassroomMapper {

    public static Classroom mapPupilDTOToClassroom(PupilDTO pupilDTOs) {
        Classroom classroom = new Classroom();
        classroom.setName(pupilDTOs.getClassName());

        return classroom;
    }

    public static Classroom mapCreatePupilDTOToClassroom(CreatePupilDTORequest pupilDTOs) {
        Classroom classroom = new Classroom();
        classroom.setName(pupilDTOs.getClassName());

        return classroom;
    }

    public static Classroom mapClassroomDTOToClassroom(ClassroomDTO classroomDTO, long teacherId, String classroomCode) {
        Classroom classroom = new Classroom();
        classroom.setName(classroomDTO.getName());
        classroom.setClassroomTeacherId(teacherId);
        classroom.setCode(classroomCode);
        classroom.setStatusId(EStatus.ACTIVE.getId());
        classroom.setCreateDate(LocalDateTime.now());

        return classroom;
    }

    public static ClassroomDTO mapClassroomToClassroomDTO(Classroom classroom, Teacher teacher) {
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setName(classroom.getName());
        classroomDTO.setClassroomTeacherName(teacher.getName());
        classroomDTO.setClassroomTeacherLastname(teacher.getLastName());
        classroomDTO.setClassroomTeacherPatronymic(teacher.getPatronymic());

        return classroomDTO;
    }

    public static ClassroomDTOSearch mapClassroomToClassroomDTOSearch(Classroom classroom, Teacher teacher) {
        ClassroomDTOSearch classroomDTO = new ClassroomDTOSearch();
        classroomDTO.setName(classroom.getName());
        classroomDTO.setClassroomTeacherName(teacher.getName());
        classroomDTO.setClassroomTeacherLastname(teacher.getLastName());
        classroomDTO.setClassroomTeacherPatronymic(teacher.getPatronymic());
        classroomDTO.setClassroomCode(classroom.getCode());

        return classroomDTO;
    }
}
