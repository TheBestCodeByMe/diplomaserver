package com.example.diploma.controller;

import com.example.diploma.dto.classroom.ClassroomDTO;
import com.example.diploma.dto.pupil.CreatePupilDTORequest;
import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.dto.schedule.CreateScheduleDTORequest;
import com.example.diploma.dto.subject.CreateSubjectDTORequest;
import com.example.diploma.dto.subject.SubjectDTO;
import com.example.diploma.dto.teacher.CreateTeacherDTORequest;
import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.model.Teacher;
import com.example.diploma.service.EditUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200") // убрать)))
@RequestMapping("/api/v1/editUsers")
@RequiredArgsConstructor
public class EditUsersController { // TODO: убрать возможность нескольких добавлений пользователя к ученику/учителю

    private final EditUsersService editUsersService;


    @PostMapping("/createPupilDTO")
    public ResponseEntity<?> createPupil(@Validated @RequestBody CreatePupilDTORequest pupilDTO) {
        return editUsersService.createPupil(pupilDTO);
    }

    @GetMapping("/showPupilDTO")
    public ResponseEntity<?> getAllPupilDTO() {
        List<PupilDTO> pupilDTOList = editUsersService.getAllPupilDTO();
        return ResponseEntity.ok(Objects.requireNonNullElse(pupilDTOList, ""));
    }

    @PostMapping("/createTeacher")
    public ResponseEntity<?> createTeacher(@Validated @RequestBody CreateTeacherDTORequest teacherDTO) {
        return editUsersService.createTeacher(teacherDTO);
    }

    @PostMapping("/createSubject")
    public ResponseEntity<?> createSubject(@Validated @RequestBody CreateSubjectDTORequest subject) {
        return editUsersService.createSubject(subject);
    }

    @PostMapping("/createSheduleDTO")
    public ResponseEntity<?> createSheduleDTO(@Validated @RequestBody CreateScheduleDTORequest sheduleDTO) {
        return editUsersService.createScheduleDTO(sheduleDTO);
    }

    @PostMapping("/createClassroomDTO")
    public ResponseEntity<?> createClassroom(@Validated @RequestBody ClassroomDTO classroomDTO) {
        return editUsersService.createClassroom(classroomDTO);
    }
/*
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
