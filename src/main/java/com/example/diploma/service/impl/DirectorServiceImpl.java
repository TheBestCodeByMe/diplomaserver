package com.example.diploma.service.impl;

import com.example.diploma.dao.ClassroomDao;
import com.example.diploma.dao.TeacherDao;
import com.example.diploma.dto.classroom.ClassroomDTO;
import com.example.diploma.mapper.ClassroomMapper;
import com.example.diploma.model.Classroom;
import com.example.diploma.model.Teacher;
import com.example.diploma.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final TeacherDao teacherDao;

    private final ClassroomDao classroomDao;

    @Override
    public List<ClassroomDTO> getAllClassroom() {
        List<Teacher> teachers = teacherDao.findAll();
        List<Classroom> classroom = classroomDao.findAll();
        List<ClassroomDTO> classroomDTOList = new ArrayList<>();

        for (Classroom value : classroom) {
            for (Teacher teacher : teachers) {
                if(Objects.equals(value.getClassroomTeacherId(), teacher.getId())){
                    classroomDTOList.add(ClassroomMapper.mapClassroomToClassroomDTO(value, teacher));}
            }
        }
        return classroomDTOList;
    }
}