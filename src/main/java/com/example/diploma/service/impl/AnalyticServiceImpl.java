package com.example.diploma.service.impl;

import com.example.diploma.dao.*;
import com.example.diploma.dto.analytic.*;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.*;
import com.example.diploma.service.AnalyticService;
import com.example.diploma.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticServiceImpl implements AnalyticService {

    private final AttendanceDao attendanceDao;

    private final AcademicPerformanceDao academicPerformanceDao;

    private final PupilDao pupilDao;

    private final ScheduleDao scheduleDao;

    private final SubjectDao subjectDao;

    private final ClassroomDao classroomDao;

    private final DiaryService diaryService;

    @Autowired
    private ModelMapper modelMapper;

    // TODO: do this method
    @Override
    public AnalyticDTOResponse getAnalyticPage(Long userId, int sem) {
        AnalyticDTOResponse analyticDTOResponse = new AnalyticDTOResponse();
        List<AcademicPerformanceInClassDTO> academicPerformanceInClassDTO = new ArrayList<>();
        List<AcademicPerformanceInMonthDTO> academicPerformanceInMonthDTO = new ArrayList<>();
        List<AttendanceInMonthDTO> attendanceInMonthDTO = new ArrayList<>();

        Pupil pupil = pupilDao.findByUserId(userId);
        List<Attendance> attendanceList = attendanceDao.findAllByPupilID(pupil.getId(), sem);
        List<AcademicPerfomance> academicPerfomanceList = academicPerformanceDao.findAllByPupilID(pupil.getId());
        List<Schedule> scheduleList = scheduleDao.findAllByClassroomID(pupil.getClassroomId());
        List<Attendance> attendanceClassList = attendanceDao.findAllByClassID(pupil.getClassroomId(), sem);
        List<AcademicPerfomance> academicPerfomanceClassList = academicPerformanceDao.findAllByClassSem(pupil.getClassroomId(), sem);

        int excellentStudents = 0;
        int goodLevel = 0;
        int satisfactorilyLevel = 0;
        int lowLevel = 0;

        scheduleList.stream().forEach(schedule -> {
            academicPerfomanceClassList.forEach(academicPerfomance -> {
                if(academicPerfomance.getGrade()>8) {excellentStudents++}
                if (academicPerfomance.getGrade()<=8||academicPerfomance.getGrade()>=6) {goodLevel++}
                if (academicPerfomance.getGrade()==4||academicPerfomance.getGrade()==5) {satisfactorilyLevel++}
                if (academicPerfomance.getGrade()<4) {lowLevel++}
            });
        });

        academicPerformanceInClassDTO.add(AcademicPerformanceInClassDTO("Отлично", excellentStudents));
        academicPerformanceInClassDTO.add(AcademicPerformanceInClassDTO("Хорошо", goodLevel));
        academicPerformanceInClassDTO.add(AcademicPerformanceInClassDTO("Удовлетворительно", satisfactorilyLevel));
        academicPerformanceInClassDTO.add(AcademicPerformanceInClassDTO("Неудовлетворительно", lowLevel));

/*        List<AcademicPerformanceInMonthDTODB> academicPerformanceInMonthDB = academicPerformanceDao.findAllBy(userId, EStatus.ACTIVE.getId(), pupil.getClassroomId(), sem);

        List<AcademicPerformanceInMonthDTODB> usersdto = academicPerformanceDao.findAllBy(userId, EStatus.ACTIVE.getId(), pupil.getClassroomId(), sem).stream().map(userprojection -> modelMapper.map(userprojection, AcademicPerformanceInMonthDTODB.class))
                .collect(Collectors.toList());*/

        analyticDTOResponse.setAcademicPerformanceInMonthDTOList(academicPerformanceInMonthDTO);
        analyticDTOResponse.setAcademicPerformanceInClassDTOList(academicPerformanceInClassDTO);
        analyticDTOResponse.setAttendanceInMonthDTOList(attendanceInMonthDTO);
        return analyticDTOResponse;
    }
}