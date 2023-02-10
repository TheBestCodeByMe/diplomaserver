package com.example.diploma.repo;

import com.example.diploma.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Classroom findClassroomByNameAndStatusId(String name, Long statusId);
    Classroom findClassroomByClassroomTeacherIdAndStatusId(Long teacherId, Long statusId);
}
