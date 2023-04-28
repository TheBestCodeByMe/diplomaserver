package com.example.diploma.dao;

import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.AcademicPerfomance;
import com.example.diploma.repo.AcademicPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AcademicPerformanceDao {

    private final AcademicPerformanceRepository academicPerformanceRepository;

    public boolean isExist(Long classId, Long lessonId, Long pupilId){
        return academicPerformanceRepository.existsByClassIDAndLessonIDAndPupilIDAndStatusId(classId, lessonId, pupilId, EStatus.ACTIVE.getId());
    }

    public AcademicPerfomance find(Long classId, Long lessonId, Long pupilId){
        return academicPerformanceRepository.findByClassIDAndLessonIDAndPupilIDAndStatusId(classId, lessonId, pupilId, EStatus.ACTIVE.getId());
    }

    public List<AcademicPerfomance> findAllByPupilID(Long id) {
        return academicPerformanceRepository.findAllByPupilIDAndStatusId(id, EStatus.ACTIVE.getId());
    }


    public List<AcademicPerfomance> findAllByPupilIDAndClassroomId(Long id, Long classId, int sem) {
        return academicPerformanceRepository.findAllByPupilStatusClassSem(id, EStatus.ACTIVE.getId(), classId, sem);
    }
}
