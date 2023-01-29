package com.example.diploma.service;

import com.example.diploma.dto.ClassroomDTO;
import com.example.diploma.dto.PupilDTO;
import com.example.diploma.dto.SheduleDTO;
import com.example.diploma.model.*;
import com.example.diploma.model.Pupil;
import com.example.diploma.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

public interface EditUsersService {
    Pupil createPupil(PupilDTO pupilDTO);

    List<PupilDTO> getAllPupilDTO();

    com.example.diploma.model.Teacher createTeacher(Teacher teacher);

    com.example.diploma.model.Subject createSubject(
        com.example.diploma.model.Subject subject);

    SheduleDTO createSheduleDTO(SheduleDTO sheduleDTO);

    ClassroomDTO createClassroom(ClassroomDTO classroomDTO);

    Map<String, Boolean> deleteUser(String login) throws ResourceNotFoundException;

    Map<String, Boolean> blockUser(String login) throws ResourceNotFoundException;

    Map<String, Boolean> unblockUser(String login);
}