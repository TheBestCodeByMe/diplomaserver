package com.example.diploma.controller;

import com.example.diploma.dto.classroom.ClassroomDTO;
import com.example.diploma.dto.pupil.CreatePupilDTORequest;
import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.dto.schedule.CreateScheduleDTORequest;
import com.example.diploma.dto.subject.CreateSubjectDTORequest;
import com.example.diploma.dto.subject.SubjectDTO;
import com.example.diploma.dto.teacher.CreateTeacherDTORequest;
import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.exception.ResourceNotFoundException;
import com.example.diploma.model.Teacher;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.service.EditUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @PostMapping("/createScheduleDTO")
    public ResponseEntity<?> createScheduleDTO(@Validated @RequestBody CreateScheduleDTORequest scheduleDTO) {
        return editUsersService.createScheduleDTO(scheduleDTO);
    }

    @PostMapping("/createClassroomDTO")
    public ResponseEntity<?> createClassroom(@Validated @RequestBody ClassroomDTO classroomDTO) {
        return editUsersService.createClassroom(classroomDTO);
    }

    @GetMapping("/deleteUser/{login}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "login") String login) throws ResourceNotFoundException {
        if (!editUsersService.deleteUser(login)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Введен неверный логин"));
        } else {
            return ResponseEntity.ok("Пользователь удален");
        }
    }

    @GetMapping("/blockUser/{login}")
    public ResponseEntity<?> blockUser(@PathVariable(value = "login") String login) throws ResourceNotFoundException {
        if (!editUsersService.blockUser(login)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Введен неверный логин"));
        } else {
            return ResponseEntity.ok("Пользователь заблокирован");
        }
    }

    @GetMapping("/unblockUser/{login}")
    public ResponseEntity<?> unblockUser(@PathVariable(value = "login") String login) {
        if (!editUsersService.unblockUser(login)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Введен неверный логин"));
        } else {
            return ResponseEntity.ok("Пользователь разблокирован");
        }
    }
}
