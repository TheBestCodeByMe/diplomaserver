package com.example.diploma.repo;

import com.example.diploma.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Classroom findClassroomByName(String name);
}
