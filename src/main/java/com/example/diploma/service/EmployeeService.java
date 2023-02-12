package com.example.diploma.service;

import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.model.Teacher;

import java.util.List;

public interface EmployeeService {
    List<TeacherDTO> getAllTeacher();

    List<TeacherDTO> getTeacherByFIO(Teacher teacher);

    TeacherDTO getTeacherByUserId(String userId);
}
