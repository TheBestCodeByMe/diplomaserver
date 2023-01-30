package com.example.diploma.controller;

import com.example.diploma.model.Teacher;
import com.example.diploma.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
/*
    @GetMapping("/getAll")
    public List<Teacher> getAllTeacher() {
        return employeeService.getAllTeacher();
    }

    @PostMapping("/getByFIO")
    public List<Teacher> getTeacherByFIO(@Validated @RequestBody Teacher teacher) {
        return employeeService.getTeacherByFIO(teacher);
    }

    @PostMapping("/getByUserId")
    public Teacher getTeacherByUserId(@RequestBody String userId) {
        return employeeService.getTeacherByUserId(userId);
    }
}*/
}
