package com.example.diploma.service.impl;

import com.example.diploma.dao.*;
import com.example.diploma.dto.diary.*;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.mapper.DiaryMapper;
import com.example.diploma.model.*;
//import com.example.diploma.mapper.Mapper;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.service.DiaryService;
import com.example.diploma.repo.*;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceDao attendanceDao;

    private final AcademicPerformanceRepository academicPerformanceRepository;
    private final AcademicPerformanceDao academicPerformanceDao;

    private final PupilRepository pupilRepository;
    private final PupilDao pupilDao;

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDao scheduleDao;

    private final SubjectRepository subjectRepository;
    private final SubjectDao subjectDao;

    private final ClassroomRepository classroomRepository;
    private final ClassroomDao classroomDao;

    @Override
    public DiaryDTOStreamProcessor addAcademicPerformance(DiaryDTOStreamProcessor diaryDTOStreamProcessor, CreateDiaryDTORequest createDiaryDTORequest) {
        AcademicPerfomance academicPerfomance = new AcademicPerfomance();

        if (!academicPerformanceDao.isExist(diaryDTOStreamProcessor.getPupil().getClassroomId(), diaryDTOStreamProcessor.getSchedule().getId(), diaryDTOStreamProcessor.getPupil().getId())) {
            academicPerfomance.setClassID(diaryDTOStreamProcessor.getPupil().getClassroomId());
            academicPerfomance.setLessonID(diaryDTOStreamProcessor.getSchedule().getId());
            academicPerfomance.setPupilID(diaryDTOStreamProcessor.getPupil().getId());
            academicPerfomance.setGrade(Integer.parseInt(createDiaryDTORequest.getGrade()));
            academicPerfomance.setCreateDate(LocalDateTime.now());
            academicPerfomance.setCloseDate(null);
            academicPerfomance.setCode(GenerationCodeServiceImpl.generateCode());
            academicPerfomance.setStatusId(EStatus.ACTIVE.getId());
            academicPerformanceRepository.save(academicPerfomance);
            diaryDTOStreamProcessor.setResponseEntity(ResponseEntity.ok().body("Оценка выставлена"));
        } else {
            academicPerfomance = academicPerformanceDao.find(diaryDTOStreamProcessor.getPupil().getClassroomId(), diaryDTOStreamProcessor.getSchedule().getId(), diaryDTOStreamProcessor.getPupil().getId());
            academicPerfomance.setGrade(Integer.parseInt(createDiaryDTORequest.getGrade()));
            academicPerformanceRepository.save(academicPerfomance);
            diaryDTOStreamProcessor.setResponseEntity(ResponseEntity.ok().body("Оценка обновлена"));
        }

        return diaryDTOStreamProcessor;
    }

    @Override
    public DiaryDTOStreamProcessor addAttendance(DiaryDTOStreamProcessor diaryDTOStreamProcessor) {
        Attendance attendance = new Attendance();

        if (!attendanceDao.isExist(diaryDTOStreamProcessor.getPupil().getClassroomId(), diaryDTOStreamProcessor.getSchedule().getId(), diaryDTOStreamProcessor.getPupil().getId())) {
            attendance.setPupilID(diaryDTOStreamProcessor.getPupil().getId());
            attendance.setClassID(diaryDTOStreamProcessor.getPupil().getClassroomId());
            attendance.setLessonID(diaryDTOStreamProcessor.getSchedule().getId());
            attendance.setCreateDate(LocalDateTime.now());
            attendance.setCloseDate(null);
            attendance.setCode(GenerationCodeServiceImpl.generateCode());
            attendance.setStatusId(EStatus.ACTIVE.getId());
            attendanceRepository.save(attendance);
            diaryDTOStreamProcessor.setResponseEntity(ResponseEntity.ok().body("Посещаемость выставлена"));
        } else {
            attendance = attendanceDao.find(diaryDTOStreamProcessor.getPupil().getClassroomId(), diaryDTOStreamProcessor.getSchedule().getId(), diaryDTOStreamProcessor.getPupil().getId());
            attendance.setStatusId(EStatus.CLOSED.getId());
            attendanceRepository.save(attendance);
            diaryDTOStreamProcessor.setResponseEntity(ResponseEntity.ok().body("Посещаемость обновлена"));
        }

        return diaryDTOStreamProcessor;
    }

    @Override
    public DiaryDTOStreamProcessor addHomework(DiaryDTOStreamProcessor diaryDTOStreamProcessor, CreateDiaryDTORequest createDiaryDTORequest) {
        diaryDTOStreamProcessor.getSchedule().setHometask(createDiaryDTORequest.getHomework());
        scheduleRepository.save(diaryDTOStreamProcessor.getSchedule());
        diaryDTOStreamProcessor.setResponseEntity(ResponseEntity.ok().body("Домашнее задание добавлено"));
        return diaryDTOStreamProcessor;
    }

    @Override
    public DiaryDTOStreamProcessor getAttendance(DiaryDTOStreamProcessor diaryDTOStreamProcessor) {
        diaryDTOStreamProcessor.setAttendance(attendanceDao.isExist(diaryDTOStreamProcessor.getPupil().getClassroomId(), diaryDTOStreamProcessor.getSchedule().getId(), diaryDTOStreamProcessor.getPupil().getId()));
        return diaryDTOStreamProcessor;
    }

    @Override
    public DiaryDTOStreamProcessor getAcademicPerformance(DiaryDTOStreamProcessor diaryDTOStreamProcessor) {
        diaryDTOStreamProcessor.setAcademicPerformance(academicPerformanceDao.isExist(diaryDTOStreamProcessor.getPupil().getClassroomId(), diaryDTOStreamProcessor.getSchedule().getId(), diaryDTOStreamProcessor.getPupil().getId()));
        return diaryDTOStreamProcessor;
    }

    @Override
    public List<DiaryDTO> getDiaryDTOByUser(Long id) {
        Pupil pupil = pupilDao.findByUserId(id);

        List<Schedule> schedules;
        List<Subject> subjects;
        List<Attendance> attendances;
        List<AcademicPerfomance> academicPerformances;

        if (pupil == null) {
            return null;
        } else {
            schedules = scheduleDao.findAllByClassroomID(pupil.getClassroomId());
            subjects = subjectDao.findAll();
            attendances = attendanceDao.findAllByPupilID(pupil.getId());
            academicPerformances = academicPerformanceDao.findAllByPupilID(pupil.getId());
        }
        List<DiaryDTO> diaryDtoList = new ArrayList<>();

        for (Schedule schedule : schedules) {
            diaryDtoList.add(getAttendanceAndGrade(pupil, academicPerformances, subjects, schedule, attendances));
        }

        return diaryDtoList;
    }

    @Override
    public int getNumbAttendance(Long id) {
        Pupil pupil = pupilDao.findByUserId(id);
        List<Attendance> attendance = attendanceDao.findAllByPupilID(pupil.getId());
        return attendance.size();
    }

    @Override
    public ResponseEntity<?> getDiaryDTOByClass(String classForSearch) {
        List<DiaryDTO> diaryDTOList = new ArrayList<>();
        Classroom classroom = classroomDao.findClassroomByName(classForSearch);
        if (classroom == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Такого класса нет"));
        }

        List<Pupil> pupilList = pupilDao.findAllByClassroomId(classroom.getId());
        List<Schedule> schedules = scheduleDao.findAllByClassroomID(classroom.getId());
        List<Subject> subjects = subjectDao.findAll();

        for (Pupil pupil : pupilList) {
            List<Attendance> attendances = attendanceDao.findAllByPupilID(pupil.getId());
            List<AcademicPerfomance> academicPerformances = academicPerformanceDao.findAllByPupilID(pupil.getId());
            for (Schedule schedule : schedules) {
                diaryDTOList.add(getAttendanceAndGrade(pupil, academicPerformances, subjects, schedule, attendances));
            }
        }

        return ResponseEntity.ok(diaryDTOList);
    }

    private DiaryDTO getAttendanceAndGrade(Pupil pupil, List<AcademicPerfomance> academicPerformances, List<Subject> subjects, Schedule schedule, List<Attendance> attendances) {
        DiaryDTO diaryDTO = new DiaryDTO();
        String grade = "";
        boolean isAttendance = false;

        for (Subject subject : subjects) {
            if (Objects.equals(schedule.getSubjectID(), subject.getId())) {
                if (academicPerformances == null) {
                    grade = "";
                } else {
                    for (AcademicPerfomance academicPerfomance : academicPerformances) {
                        if (Objects.equals(academicPerfomance.getLessonID(), schedule.getId())) {
                            grade = String.valueOf(academicPerfomance.getGrade());
                        } else {
                            grade = "";
                        }
                    }
                }
                if (attendances != null) {
                    for (Attendance attendance : attendances) {
                        isAttendance = Objects.equals(schedule.getId(), attendance.getLessonID());
                    }
                }
                diaryDTO = DiaryMapper.mapToDiaryDTO(schedule, pupil, classroomRepository.getById(pupil.getClassroomId()), isAttendance, grade, subject);
            }
        }

        return diaryDTO;
    }

    @Override
    public double getAverageGrade(Long id) {
        Pupil pupil = pupilRepository.findByUserId(id);
        List<AcademicPerfomance> academicPerformanceList = academicPerformanceDao.findAllByPupilID(pupil.getId());

        double sumGrade = 0;
        for (AcademicPerfomance academicPerfomance : academicPerformanceList) {
            sumGrade += academicPerfomance.getGrade();
        }
        if (sumGrade != 0) {
            return sumGrade / academicPerformanceList.size();
        } else {
            return 0;
        }
    }

    @Override
    public void saveGradesByUserId(Long userId) {
        List<DiaryDTO> diaryDTOList = getDiaryDTOByUser(userId);
        if (diaryDTOList != null) {
            try {
                ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
                ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
                objectWriter.writeValue(new File("D:\\diplomaserver\\src\\main\\resources\\academicPerformances\\" + diaryDTOList.get(0).getLastnamePupil() + "diary.json"), diaryDTOList);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public ResponseEntity<?> getDiariesByClassAndSubject(String classname, String subject) {
        DiaryBySubjectDTOResponse diaryBySubjectDTOResponse = new DiaryBySubjectDTOResponse();
        List<DiaryBySubjectDTOList> diaryBySubjectDTOList = new ArrayList<>();
        List<DiaryBySubjectDTO> diaryBySubjectDTOs = new ArrayList<>();
        Classroom classroom = classroomDao.findClassroomByName(classname);
        if (classroom == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Такого класса нет"));
        }

        List<Pupil> pupilList = pupilDao.findAllByClassroomId(classroom.getId());
        List<Schedule> schedules = scheduleDao.findAllByClassroomID(classroom.getId());
        List<Subject> subjects = subjectDao.findAll();

        for (Pupil pupil : pupilList) {
            List<Attendance> attendances = attendanceDao.findAllByPupilID(pupil.getId());
            List<AcademicPerfomance> academicPerformances = academicPerformanceDao.findAllByPupilID(pupil.getId());
            diaryBySubjectDTOs.addAll(getAttendanceAndGrade(academicPerformances, subjects, schedules, attendances));
        }

        return diaryBySubjectDTOResponse;
    }

    private Collection<DiaryBySubjectDTO> getAttendanceAndGrade(List<AcademicPerfomance> academicPerformances, List<Subject> subjects, List<Schedule> schedules, List<Attendance> attendances) {
        Collection<DiaryBySubjectDTO> diaryDTO = new ArrayList<>();
        String grade = "";
        boolean isAttendance = false;

        for (Subject subject : subjects) {
            for (Schedule schedule : schedules) {
                if (Objects.equals(schedule.getSubjectID(), subject.getId())) {
                    if (academicPerformances == null) {
                        grade = "";
                    } else {
                        for (AcademicPerfomance academicPerfomance : academicPerformances) {
                            if (Objects.equals(academicPerfomance.getLessonID(), schedule.getId())) {
                                grade = String.valueOf(academicPerfomance.getGrade());
                            } else {
                                grade = "";
                            }
                        }
                    }
                    if (attendances != null) {
                        for (Attendance attendance : attendances) {
                            isAttendance = Objects.equals(schedule.getId(), attendance.getLessonID());
                        }
                    }
                    diaryDTO.add(DiaryMapper.mapToDiaryBySubjectDTO(schedule, isAttendance, grade));
                }
            }
        }

        return diaryDTO;
    }
}