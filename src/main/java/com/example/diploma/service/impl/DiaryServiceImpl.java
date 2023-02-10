package com.example.diploma.service.impl;

import com.example.diploma.dao.*;
import com.example.diploma.dto.diary.CreateDiaryDTORequest;
import com.example.diploma.dto.diary.DiaryDTO;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.mapper.DiaryMapper;
import com.example.diploma.model.*;
//import com.example.diploma.mapper.Mapper;
import com.example.diploma.service.DiaryService;
import com.example.diploma.repo.*;
import com.example.diploma.stream.DiaryDTOStreamProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

        if(!academicPerformanceDao.isExist(diaryDTOStreamProcessor.getPupil().getClassroomId(), diaryDTOStreamProcessor.getSchedule().getId(), diaryDTOStreamProcessor.getPupil().getId())) {
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

        if(!attendanceDao.isExist(diaryDTOStreamProcessor.getPupil().getClassroomId(), diaryDTOStreamProcessor.getSchedule().getId(), diaryDTOStreamProcessor.getPupil().getId())) {
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
    public  DiaryDTOStreamProcessor addHomework(DiaryDTOStreamProcessor diaryDTOStreamProcessor, CreateDiaryDTORequest createDiaryDTORequest) {

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

        if(pupil == null) {
            return null;
        } else {
            schedules = scheduleDao.findAllByClassroomID(pupil.getClassroomId());
            subjects = subjectDao.findAll();
            attendances = attendanceDao.findAllByPupilID(pupil.getId());
            academicPerformances = academicPerformanceDao.findAllByPupilID(pupil.getId());
        }
        List<DiaryDTO> diaryDtoList = new ArrayList<>();

        boolean attendanceBoolean = false;
        String grade = "";

        for (Schedule schedule : schedules) {
            DiaryDTO diaryDTO = new DiaryDTO();
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
                            attendanceBoolean = Objects.equals(schedule.getId(), attendance.getLessonID());
                        }
                    } else {
                        attendanceBoolean = false;
                    }
                    diaryDTO = DiaryMapper.mapToDiaryDTO(schedule, pupil, classroomRepository.getById(pupil.getClassroomId()), attendanceBoolean, grade, subject);
                }
            }
            diaryDtoList.add(diaryDTO);
        }

        return diaryDtoList;
    }

    @Override
    public int getNumbAttendance(Long id) {
        Pupil pupil = pupilDao.findByUserId(id);
        List<Attendance> attendance = attendanceDao.findAllByPupilID(pupil.getId());
        return attendance.size();
    }
/*
    // TODO: упростить, как в scheduleController
    @Override
    public List<CreateDiaryDTORequest> getDiaryDTOByClass(String classForSearch) {
        List<CreateDiaryDTORequest> createDiaryDTORequestList = new ArrayList<>();
        CreateDiaryDTORequest createDiaryDTORequest = new CreateDiaryDTORequest();
        Classroom classroom = classroomRepository.findClassroomByName(classForSearch);
        if (classroom == null) {
            createDiaryDTORequest.setNamePupil("Такого класса нет");
            createDiaryDTORequestList.add(createDiaryDTORequest);
            return createDiaryDTORequestList;
        }

        List<Pupil> pupilList = pupilRepository.findAllByClassroomId(classroom.getId());
        List<Shedule> shedules = scheduleRepository.findAllByClassroomID(classroom.getId());
        List<Subject> subjects = subjectRepository.findAll();

        boolean attendanceBoolean = false;
        String grade = "";

        for (Pupil pupil : pupilList) {
            List<Attendance> attendances = attendanceRepository.findAllByPupilID(pupil.getId());
            List<AcademicPerfomance> academicPerfomances = academicPerfomanceRepository.findAllByPupilID(pupil.getId());
            for (Shedule shedule : shedules) {
                createDiaryDTORequest = new CreateDiaryDTORequest();
                for (Subject subject : subjects) {
                    if (Objects.equals(shedule.getSubjectID(), subject.getId())) {
                        if (academicPerfomances == null) {
                            grade = "";
                        } else {
                            for (AcademicPerfomance academicPerfomance : academicPerfomances) {
                                if (Objects.equals(academicPerfomance.getLessonID(), shedule.getId())) {
                                    grade = String.valueOf(academicPerfomance.getGrade());
                                } else {
                                    grade = "";
                                }
                            }
                        }
                        if (attendances != null) {
                            for (Attendance attendance : attendances) {
                                attendanceBoolean = Objects.equals(shedule.getId(), attendance.getLessonID());
                            }
                        } else {
                            attendanceBoolean = false;
                        }
                        createDiaryDTORequest = Mapper.mapToDiaryDTO(shedule, pupil, classroomRepository.getById(pupil.getClassroomId()), attendanceBoolean, grade, subject);
                    }
                }
                createDiaryDTORequestList.add(createDiaryDTORequest);
            }
        }

        return createDiaryDTORequestList;
    }
*/
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
/*
    @Override
    public void saveGradesByUserId(Long userId) {
        List<CreateDiaryDTORequest> createDiaryDTORequestList = getDiaryDTOByUser(userId);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
            objectWriter.writeValue(new File("D:\\BSUIR\\6semestr\\CourseWork\\Programm\\diploma\\server\\src\\main\\resources\\" + createDiaryDTORequestList.get(0).getLastnamePupil() + "diary.json"), createDiaryDTORequestList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
}