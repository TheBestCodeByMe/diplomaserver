package com.example.diploma.service.impl;


import com.example.diploma.service.SheduleService;
import com.example.diploma.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SheduleServiceImpl implements SheduleService {

    private final AttendanceRepository attendanceRepository;

    private final AcademicPerfomanceRepository academicPerfomanceRepository;

    private final PupilRepository pupilRepository;

    private final ScheduleRepository scheduleRepository;

    private final SubjectRepository subjectRepository;

    private final ClassroomRepository classroomRepository;

    private final TeacherRepository teacherRepository;

    private final CalendarRepository calendarRepository;
/*
    @Override
    public ResponseEntity<List<SheduleDTO>> getScheduleByIdAndDate(@PathVariable(value = "userId") Long userId, @PathVariable(value = "date") String date)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(getScheduleDTOByIdAndDate(userId, date));
    }*/
/*
    @Override
    public List<SheduleDTO> getScheduleDTOByIdAndDate(Long userId, String date) {
        Pupil pupil = pupilRepository.findByUserId(userId);
        List<Shedule> sheduleList = sheduleRepository.findAllByClassroomIDAndDate(pupil.getClassroomId(), Date.valueOf(date));
        List<SheduleDTO> sheduleDTOList = new ArrayList<>();

        Classroom classroom = classroomRepository.getById(pupil.getClassroomId());

        for (Shedule shedule : sheduleList) {
            Subject subject = subjectRepository.getById(shedule.getSubjectID());
            Teacher teacher = teacherRepository.getById(shedule.getTeacherID());
            Calendar calendar = calendarRepository.getById(shedule.getCalendarId());

            SheduleDTO sheduleDTO = Mapper.mapSheduleToSheduleDTO(shedule, calendar, subject, teacher, classroom);
            sheduleDTOList.add(sheduleDTO);
        }
        return sheduleDTOList;
    }*/

}