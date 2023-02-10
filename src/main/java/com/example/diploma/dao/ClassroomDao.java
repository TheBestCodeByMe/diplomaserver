package com.example.diploma.dao;

import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Classroom;
import com.example.diploma.model.Parents;
import com.example.diploma.repo.ClassroomRepository;
import com.example.diploma.repo.ParentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClassroomDao {

    private final ClassroomRepository classroomRepository;

    public Classroom findByClassroomId(Long classroomId) {
        return classroomRepository.getById(classroomId);
    }

    public Classroom findClassroomByName(String name) {return classroomRepository.findClassroomByNameAndStatusId(name, EStatus.ACTIVE.getId());}

    public Classroom findClassroomByTeacherId(Long teacherId) {return classroomRepository.findClassroomByClassroomTeacherIdAndStatusId(teacherId, EStatus.ACTIVE.getId());}
}
