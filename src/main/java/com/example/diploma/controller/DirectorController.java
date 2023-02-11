package com.example.diploma.controller;

import com.example.diploma.dto.classroom.ClassroomDTO;
import com.example.diploma.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping("/classroomDTO")
    public ResponseEntity<?> getAllClassroom() {
        List<ClassroomDTO> classroomDTOList = directorService.getAllClassroom();
        return ResponseEntity.ok(Objects.requireNonNullElse(classroomDTOList, ""));
    }
}
