package com.example.diploma.service.impl;

import com.example.diploma.model.Teacher;
import com.example.diploma.service.EmployeeService;
import com.example.diploma.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> getTeacherByFIO(Teacher teacher) {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacherRepository.findByNameAndLastNameAndPatronymic(teacher.getName(), teacher.getLastName(), teacher.getPatronymic()));
        return teacherList;
    }

    @Override
    public Teacher getTeacherByUserId(String userId) {
        return teacherRepository.findByUserId(Long.parseLong(userId));
    }
}