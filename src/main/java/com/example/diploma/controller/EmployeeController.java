package com.example.diploma.controller;

import com.example.diploma.model.Teacher;
import com.example.diploma.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTeacher() {
        return ResponseEntity.ok(Objects.requireNonNullElse(employeeService.getAllTeacher(), ""));
    }

    @PostMapping("/getByFIO")
    public ResponseEntity<?> getTeacherByFIO(@Validated @RequestBody Teacher teacher) {
        return ResponseEntity.ok(Objects.requireNonNullElse(employeeService.getTeacherByFIO(teacher), ""));
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getTeacherByUserId(@PathVariable(value = "userId") String userId) {
        return ResponseEntity.ok(Objects.requireNonNullElse(employeeService.getTeacherByUserId(userId), ""));
    }
}
