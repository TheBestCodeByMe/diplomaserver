package com.example.diploma.service;

import com.example.diploma.dto.classroom.ClassroomDTO;
import com.example.diploma.dto.pupil.CreatePupilDTORequest;
import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.dto.schedule.CreateScheduleDTORequest;
import com.example.diploma.dto.subject.CreateSubjectDTORequest;
import com.example.diploma.dto.subject.SubjectDTO;
import com.example.diploma.dto.teacher.CreateTeacherDTORequest;
import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.model.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EditUsersService {
    ResponseEntity<?> createPupil(CreatePupilDTORequest pupilDTO);

    List<PupilDTO> getAllPupilDTO();

    ResponseEntity<?> createTeacher(CreateTeacherDTORequest teacherDTO);

    ResponseEntity<?> createSubject(CreateSubjectDTORequest subject);

    ResponseEntity<?> createScheduleDTO(CreateScheduleDTORequest sheduleDTO);

    ResponseEntity<?> createClassroom(ClassroomDTO classroomDTO);
/*
    Map<String, Boolean> deleteUser(String login) throws ResourceNotFoundException;

    Map<String, Boolean> blockUser(String login) throws ResourceNotFoundException;

    Map<String, Boolean> unblockUser(String login);*/
}