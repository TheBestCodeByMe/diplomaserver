package com.example.diploma.repo;

import com.example.diploma.dto.analytic.AcademicPerformanceInMonthDTODB;
import com.example.diploma.dto.analytic.AcademicPerformanceInMonthInt;
import com.example.diploma.model.AcademicPerfomance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcademicPerformanceRepository extends JpaRepository<AcademicPerfomance, Long> {
    boolean existsByClassIDAndLessonIDAndPupilIDAndStatusId(Long classId, Long lessonId, Long pupilId, Long statusId);
    AcademicPerfomance findByClassIDAndLessonIDAndPupilIDAndStatusId(Long classId, Long lessonId, Long pupilId, Long statusId);
    List<AcademicPerfomance> findAllByPupilIDAndStatusId(Long pupilId, Long statusId);

    @Query("select a from AcademicPerfomance a, Schedule s, Calendar c where a.pupilID = ?1 and a.statusId = ?2 and a.lessonID = s.id and s.calendarId = c.id and c.semesterID = ?4")
    List<AcademicPerfomance> findAllByPupilStatusSem(Long pupilId, Long statusId, int sem);
    @Query("select a from AcademicPerfomance a, Schedule s, Calendar c where a.classID = ?1 and a.statusId = ?2 and a.lessonID = s.id and s.calendarId = c.id and c.semesterID = ?4")
    List<AcademicPerfomance> findAllByClassStatusSem(Long classId, Long statusId, int sem);

//    @Query(value = """
//            select s.schedule_date as date, AVG(a.academic_performance_grade) as averageGrade
//            from academic_performance a, schedule s, calendar c
//            where a.pupil_academic_performance_id = ?1 and a.academic_performance_status_id = ?2 and a.class_academic_performance_id = ?3
//            and a.lesson_academic_performance_id = s.schedule_id and s.calendar_schedule_id = c.calendar_id and c.calendar_semester = ?4 order by s.schedule_date""", nativeQuery = true) // , nativeQuery=true
//    List<AcademicPerformanceInMonthInt> findAvgGrades(Long pupilId, Long statusId, Long classId, int sem);


//    @Query(value = """
//            select new com.example.diploma.dto.analytic.AcademicPerformanceInMonthDTODB(s.schedule_date, a.academic_performance_grade)
//            from academic_performance a, schedule s, calendar c
//            where a.pupil_academic_performance_id = ?1 and a.academic_performance_status_id = ?2 and a.class_academic_performance_id = ?3
//            and a.lesson_academic_performance_id = s.schedule_id and s.calendar_schedule_id = c.calendar_id and c.calendar_semester = ?4 order by s.schedule_date""", nativeQuery = true) // , nativeQuery=true
//    List<AcademicPerformanceInMonthDTODB> findAvgGrades(Long pupilId, Long statusId, Long classId, int sem);}
