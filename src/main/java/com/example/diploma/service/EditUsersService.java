package com.example.diploma.service;

import com.example.diploma.dto.classroom.ClassroomDTO;
import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.model.Teacher;
import org.springframework.http.ResponseEntity;

public interface EditUsersService {
   /* Pupil createPupil(PupilDTO pupilDTO);

    List<PupilDTO> getAllPupilDTO();
*/
    ResponseEntity<?> createTeacher(TeacherDTO teacherDTO);
/*
    com.example.diploma.model.Subject createSubject(
        com.example.diploma.model.Subject subject);

    SheduleDTO createSheduleDTO(SheduleDTO sheduleDTO);
*/
    ResponseEntity<?> createClassroom(ClassroomDTO classroomDTO);
/*
    Map<String, Boolean> deleteUser(String login) throws ResourceNotFoundException;

    Map<String, Boolean> blockUser(String login) throws ResourceNotFoundException;

    Map<String, Boolean> unblockUser(String login);*/
}