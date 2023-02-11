package com.example.diploma.service.impl;

import com.example.diploma.dao.TeacherDao;
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

    private final TeacherDao teacherDao;

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherDao.findAll();
    }

    @Override
    public List<Teacher> getTeacherByFIO(Teacher teacher) {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacherDao.findByFio(teacher.getName(), teacher.getLastName(), teacher.getPatronymic()));
        return teacherList;
    }

    @Override
    public Teacher getTeacherByUserId(String userId) {
        return teacherDao.findByUserId(Long.parseLong(userId));
    }
}