package com.example.diploma.service.impl;

import com.example.diploma.dao.TeacherDao;
import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.mapper.TeacherMapper;
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
    public List<TeacherDTO> getAllTeacher() {
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        List<Teacher> teacherList = teacherDao.findAll();
        for (Teacher teacher : teacherList) {
            teacherDTOList.add(TeacherMapper.mapToTeacherDto(teacher));
        }
        return teacherDTOList;
    }

    @Override
    public List<TeacherDTO> getTeacherByFIO(Teacher teacher) {
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        Teacher searchedTeacher = teacherDao.findByFio(teacher.getName(), teacher.getLastName(), teacher.getPatronymic());
        if (searchedTeacher != null) {
            teacherDTOList.add(TeacherMapper.mapToTeacherDto(searchedTeacher));
        }
        return teacherDTOList;
    }

    @Override
    public TeacherDTO getTeacherByUserId(String userId) {
        Teacher teacher = teacherDao.findByUserId(Long.parseLong(userId));
        if (teacher != null) {
            return TeacherMapper.mapToTeacherDto(teacher);
        } else {
            return null;
        }
    }
}