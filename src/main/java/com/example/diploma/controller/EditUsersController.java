package com.example.diploma.controller;

import com.example.diploma.dto.ClassroomDTO;
import com.example.diploma.dto.PupilDTO;
import com.example.diploma.dto.SheduleDTO;
import com.example.diploma.model.*;
import com.example.diploma.exception.ResourceNotFoundException;
import com.example.diploma.service.EditUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // убрать)))
@RequestMapping("/api/v1/editUsers")
@RequiredArgsConstructor
public class EditUsersController { // TODO: убрать возможность нескольких добавлений пользователя к ученику/учителю

    private final EditUsersService editUsersService;
/*
    @PostMapping("/createPupilDTO")
    public Pupil createPupil(@Validated @RequestBody PupilDTO pupilDTO) {
        return editUsersService.createPupil(pupilDTO);
    }

    @GetMapping("/showPupilDTO")
    public List<PupilDTO> getAllPupilDTO() {
        return editUsersService.getAllPupilDTO();
    }

    @PostMapping("/createTeacher")
    public Teacher createTeacher(@Validated @RequestBody Teacher teacher) {
        return editUsersService.createTeacher(teacher);
    }

    @PostMapping("/createSubject")
    public Subject createSubject(@Validated @RequestBody Subject subject) {
        return editUsersService.createSubject(subject);
    }

    @PostMapping("/createSheduleDTO")
    public SheduleDTO createSheduleDTO(@Validated @RequestBody SheduleDTO sheduleDTO) {
        return editUsersService.createSheduleDTO(sheduleDTO);
    }

    @PostMapping("/createClassroomDTO")
    public ClassroomDTO createClassroom(@Validated @RequestBody ClassroomDTO classroomDTO) {
        return editUsersService.createClassroom(classroomDTO);
    }

    @DeleteMapping("/deleteUser/{login}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "login") String login)
            throws ResourceNotFoundException {
        return editUsersService.deleteUser(login);
    }

    @PostMapping("/blockUser/{login}")
    public Map<String, Boolean> blockUser(@PathVariable(value = "login") String login)
            throws ResourceNotFoundException {
        return editUsersService.blockUser(login);
    }

    @PostMapping("/unblockUser/{login}")
    public Map<String, Boolean> unblockUser(@PathVariable(value = "login") String login) {
        return editUsersService.unblockUser(login);
    }*/
}
