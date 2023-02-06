package com.example.diploma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DirectorController {
/*
    private final DirectorService directorService;

    @GetMapping("/classroomDTO")
    public List<ClassroomDTO> getAllClassroom() {
        return directorService.getAllClassroom();
    }*/
}
