package com.example.diploma.service.impl;

import com.example.diploma.dto.ClassroomDTO;
import com.example.diploma.model.Classroom;
import com.example.diploma.model.Teacher;
import com.example.diploma.mapper.Mapper;
import com.example.diploma.service.DirectorService;
import com.example.diploma.repo.ClassroomRepository;
import com.example.diploma.repo.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {
/*
    private final TeacherRepository teacherRepository;

    private final ClassroomRepository classroomRepository;

    @Override
    public List<ClassroomDTO> getAllClassroom() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<Classroom> classroom = classroomRepository.findAll();
        List<ClassroomDTO> classroomDTOList = new ArrayList<>();

        for (Classroom value : classroom) {
            for (Teacher teacher : teachers) {
                if(Objects.equals(value.getClassroomTeacherId(), teacher.getId())){
                    classroomDTOList.add(Mapper.mapClassroomToClassroomDTO(value, teacher));}
            }
        }
        return classroomDTOList;
    }*/
}