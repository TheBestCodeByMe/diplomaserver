package com.example.diploma.service.impl;

import com.example.diploma.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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