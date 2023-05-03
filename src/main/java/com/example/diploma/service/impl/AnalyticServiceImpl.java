package com.example.diploma.service.impl;

import com.example.diploma.dao.*;
import com.example.diploma.dto.analytic.*;
import com.example.diploma.model.*;
import com.example.diploma.service.AnalyticService;
import com.example.diploma.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Override
    public AnalyticDTOResponse getAnalyticPage(Long userId, int sem) {
        AnalyticDTOResponse analyticDTOResponse = new AnalyticDTOResponse();

        Pupil pupil = pupilDao.findByUserId(userId);
        List<Attendance> attendanceList = attendanceDao.findAllByPupilID(pupil.getId(), sem);
        System.out.println(pupil);
        System.out.println(attendanceList);
        List<AcademicPerfomance> academicPerformanceList = academicPerformanceDao.findAllByPupilID(pupil.getId());
        System.out.println(academicPerformanceList);
        List<Schedule> scheduleList = scheduleDao.findAllByClassroomID(pupil.getClassroomId());
        System.out.println(scheduleList);
        List<AcademicPerfomance> academicPerformanceClassList = academicPerformanceDao.findAllByClassSem(pupil.getClassroomId(), sem);
        System.out.println(academicPerformanceClassList);

/*        List<AcademicPerformanceInMonthDTODB> academicPerformanceInMonthDB = academicPerformanceDao.findAllBy(userId, EStatus.ACTIVE.getId(), pupil.getClassroomId(), sem);

        List<AcademicPerformanceInMonthDTODB> usersdto = academicPerformanceDao.findAllBy(userId, EStatus.ACTIVE.getId(), pupil.getClassroomId(), sem).stream().map(userprojection -> modelMapper.map(userprojection, AcademicPerformanceInMonthDTODB.class))
                .collect(Collectors.toList());*/

        analyticDTOResponse.setAcademicPerformanceInMonthDTOList(getAcademicPerformanceInMonth(academicPerformanceList, scheduleList));
        analyticDTOResponse.setAcademicPerformanceInClassDTOList(getAcademicPerformance(academicPerformanceClassList));
        analyticDTOResponse.setAttendanceInMonthDTOList(getAttendanceInMonth(attendanceList, scheduleList));
        System.out.println(analyticDTOResponse);
        return analyticDTOResponse;
    }

    private List<AcademicPerformanceInMonthDTO> getAcademicPerformanceInMonth(List<AcademicPerfomance> academicPerformanceList, List<Schedule> scheduleList) {
        List<AcademicPerformanceInMonthDTO> academicPerformanceInMonthDTO = new ArrayList<>();

        academicPerformanceInMonthDTO.add(new AcademicPerformanceInMonthDTO("January", 0));
        academicPerformanceInMonthDTO.add(new AcademicPerformanceInMonthDTO("February", 0));
        academicPerformanceInMonthDTO.add(new AcademicPerformanceInMonthDTO("March", 0));
        academicPerformanceInMonthDTO.add(new AcademicPerformanceInMonthDTO("April", 0));
        academicPerformanceInMonthDTO.add(new AcademicPerformanceInMonthDTO("May", 0));
        academicPerformanceInMonthDTO.add(new AcademicPerformanceInMonthDTO("September", 0));
        academicPerformanceInMonthDTO.add(new AcademicPerformanceInMonthDTO("October", 0));
        academicPerformanceInMonthDTO.add(new AcademicPerformanceInMonthDTO("November", 0));
        academicPerformanceInMonthDTO.add(new AcademicPerformanceInMonthDTO("December", 0));

        Map<String, AtomicInteger> stringAtomicIntegerMap = new HashMap<>();

        AtomicInteger januaryCount = new AtomicInteger();
        AtomicInteger februaryCount = new AtomicInteger();
        AtomicInteger marchCount = new AtomicInteger();
        AtomicInteger aprilCount = new AtomicInteger();
        AtomicInteger mayCount = new AtomicInteger();
        AtomicInteger septemberCount = new AtomicInteger();
        AtomicInteger octoberCount = new AtomicInteger();
        AtomicInteger novemberCount = new AtomicInteger();
        AtomicInteger decemberCount = new AtomicInteger();

        stringAtomicIntegerMap.put("january", januaryCount);
        stringAtomicIntegerMap.put("february", februaryCount);
        stringAtomicIntegerMap.put("march", marchCount);
        stringAtomicIntegerMap.put("april", aprilCount);
        stringAtomicIntegerMap.put("may", mayCount);
        stringAtomicIntegerMap.put("september", septemberCount);
        stringAtomicIntegerMap.put("october", octoberCount);
        stringAtomicIntegerMap.put("november", novemberCount);
        stringAtomicIntegerMap.put("december", decemberCount);

        academicPerformanceList.forEach(academicPerformance -> {
            academicPerformanceInMonthDTO.stream().filter(it -> Objects.equals(it.getMonth().toUpperCase(Locale.ROOT), scheduleList.stream().filter(schedule1 -> Objects.equals(schedule1.getId(), academicPerformance.getLessonID())).findFirst().get().getDate().getMonth().name()))
                    .findFirst().get().setAverageGrade(academicPerformanceInMonthDTO.stream().filter(it -> Objects.equals(it.getMonth().toUpperCase(Locale.ROOT), scheduleList.stream().filter(schedule1 -> Objects.equals(schedule1.getId(), academicPerformance.getLessonID())).findFirst().get().getDate().getMonth().name()))
                            .findFirst().get().getAverageGrade() + academicPerformance.getGrade());
            stringAtomicIntegerMap.get(scheduleList.stream().filter(schedule1 -> Objects.equals(schedule1.getId(), academicPerformance.getLessonID())).findFirst().get().getDate().getMonth().name().toLowerCase(Locale.ROOT)).getAndIncrement();
        });

        academicPerformanceInMonthDTO.forEach(academicPerformance -> {
            if (academicPerformance.getAverageGrade() != 0)
                academicPerformance.setAverageGrade(academicPerformance.getAverageGrade() / stringAtomicIntegerMap.get(academicPerformance.getMonth().toLowerCase(Locale.ROOT)).get());
        });

        return academicPerformanceInMonthDTO;
    }

    private List<AttendanceInMonthDTO> getAttendanceInMonth(List<Attendance> attendanceList, List<Schedule> scheduleList) {
        List<AttendanceInMonthDTO> attendanceInMonthDTO = new ArrayList<>();

        attendanceInMonthDTO.add(new AttendanceInMonthDTO("January", 0));
        attendanceInMonthDTO.add(new AttendanceInMonthDTO("February", 0));
        attendanceInMonthDTO.add(new AttendanceInMonthDTO("March", 0));
        attendanceInMonthDTO.add(new AttendanceInMonthDTO("April", 0));
        attendanceInMonthDTO.add(new AttendanceInMonthDTO("May", 0));
        attendanceInMonthDTO.add(new AttendanceInMonthDTO("September", 0));
        attendanceInMonthDTO.add(new AttendanceInMonthDTO("October", 0));
        attendanceInMonthDTO.add(new AttendanceInMonthDTO("November", 0));
        attendanceInMonthDTO.add(new AttendanceInMonthDTO("December", 0));

        attendanceList.forEach(attendance -> {
            attendanceInMonthDTO.stream().filter(it -> Objects.equals(it.getMonth().toUpperCase(Locale.ROOT), scheduleList.stream().filter(schedule1 -> Objects.equals(schedule1.getId(), attendance.getLessonID())).findFirst().get().getDate().getMonth().name()))
                    .findFirst().get().setCount(attendanceInMonthDTO.stream().filter(it -> Objects.equals(it.getMonth().toUpperCase(Locale.ROOT), scheduleList.stream().filter(schedule1 -> Objects.equals(schedule1.getId(), attendance.getLessonID())).findFirst().get().getDate().getMonth().name()))
                            .findFirst().get().getCount() + 1);
        });

        return attendanceInMonthDTO;
    }

    private List<AcademicPerformanceInClassDTO> getAcademicPerformance(List<AcademicPerfomance> academicPerformanceClassList) {
        List<AcademicPerformanceInClassDTO> academicPerformanceInClassDTO = new ArrayList<>();

        AtomicInteger excellentStudents = new AtomicInteger();
        AtomicInteger goodLevel = new AtomicInteger();
        AtomicInteger satisfactorilyLevel = new AtomicInteger();
        AtomicInteger lowLevel = new AtomicInteger();

        academicPerformanceClassList.forEach(academicPerformance -> {
            if (academicPerformance.getGrade() > 8) {
                excellentStudents.getAndIncrement();
            }
            if (academicPerformance.getGrade() <= 8 && academicPerformance.getGrade() >= 6) {
                goodLevel.getAndIncrement();
            }
            if (academicPerformance.getGrade() == 4 || academicPerformance.getGrade() == 5) {
                satisfactorilyLevel.getAndIncrement();
            }
            if (academicPerformance.getGrade() < 4) {
                lowLevel.getAndIncrement();
            }
        });

        academicPerformanceInClassDTO.add(new AcademicPerformanceInClassDTO("Отлично", excellentStudents.get()));
        academicPerformanceInClassDTO.add(new AcademicPerformanceInClassDTO("Хорошо", goodLevel.get()));
        academicPerformanceInClassDTO.add(new AcademicPerformanceInClassDTO("Удовлетворительно", satisfactorilyLevel.get()));
        academicPerformanceInClassDTO.add(new AcademicPerformanceInClassDTO("Неудовлетворительно", lowLevel.get()));

        return academicPerformanceInClassDTO;
    }
}