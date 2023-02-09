package com.example.diploma.dao;

import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Attendance;
import com.example.diploma.model.Classroom;
import com.example.diploma.repo.AttendanceRepository;
import com.example.diploma.repo.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AttendanceDao {

    private final AttendanceRepository attendanceRepository;

    public boolean isExist(Long classId, Long lessonId, Long pupilId){
        return attendanceRepository.existsByClassIDAndLessonIDAndPupilIDAndStatusId(classId, lessonId, pupilId, EStatus.ACTIVE.getId());
    }

    public Attendance find(Long classId, Long lessonId, Long pupilId){
        return attendanceRepository.findByClassIDAndLessonIDAndPupilIDAndStatusId(classId, lessonId, pupilId, EStatus.ACTIVE.getId());
    }
}
