package com.example.diploma.controller;

import com.example.diploma.service.PupilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/pupil")
@RequiredArgsConstructor
public class PupilController {

    private final PupilService pupilService;

    @GetMapping("/getByUserId/{id}")
    public ResponseEntity<?> getPupilByFIO(@PathVariable(value = "id") String userId) {
        return ResponseEntity.ok(Objects.requireNonNullElse(pupilService.getPupilByUserId(userId), ""));
    }

    @GetMapping("/getByClass/{classname}")
    public ResponseEntity<?> getPupilsByClassName(@PathVariable(value = "classname") String classname) {
        return ResponseEntity.ok(Objects.requireNonNullElse(pupilService.getPupilsByClassName(classname), ""));
    }
}