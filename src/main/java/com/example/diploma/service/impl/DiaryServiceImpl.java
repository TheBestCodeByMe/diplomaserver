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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

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
    public void updateAcademicPerformanceStatus(AcademicPerfomance academicPerfomance, EStatus status) {
        academicPerfomance.setStatusId(status.getId());
        academicPerformanceRepository.save(academicPerfomance);
    }

    @Override
    public void updateAttendanceStatus(Attendance attendance, EStatus status) {
        attendance.setStatusId(status.getId());
        attendanceRepository.save(attendance);
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
        diaryDTOStreamProcessor.setAttendance(attendanceDao.find(diaryDTOStreamProcessor.getPupil().getClassroomId(), diaryDTOStreamProcessor.getSchedule().getId(), diaryDTOStreamProcessor.getPupil().getId()));
        diaryDTOStreamProcessor.setAttend(diaryDTOStreamProcessor.getAttendance() != null);
        return diaryDTOStreamProcessor;
    }

    @Override
    public DiaryDTOStreamProcessor getAcademicPerformance(DiaryDTOStreamProcessor diaryDTOStreamProcessor) {
        diaryDTOStreamProcessor.setAcademicPerformance(academicPerformanceDao.find(diaryDTOStreamProcessor.getPupil().getClassroomId(), diaryDTOStreamProcessor.getSchedule().getId(), diaryDTOStreamProcessor.getPupil().getId()));
        diaryDTOStreamProcessor.setAcademicPerform(diaryDTOStreamProcessor.getAcademicPerformance() != null);
        return diaryDTOStreamProcessor;
    }

    // TODO: херня
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
    public List<DiaryDTO> getDiaryDTOByParam(Long id, String param, int sem, boolean flag) {
        Pupil pupil = pupilDao.findByUserId(id);

        List<Schedule> schedules;
        List<Subject> subjects = new ArrayList<>();
        List<Attendance> attendances;
        List<AcademicPerfomance> academicPerformances;

        if (pupil == null) {
            return null;
        } else {
            if (flag) {
                DateTimeFormatter formatter = ofPattern("dd.MM.yyyy");
                LocalDate date = parse(param, formatter);
                schedules = scheduleDao.findAllByClassDateSem(pupil.getClassroomId(), date, sem);
                subjects = subjectDao.findAll();
            } else {
                subjects.add(subjectDao.findBySubjectCode(param));
                schedules = scheduleDao.findAllByClassSemSubj(pupil.getClassroomId(), sem, subjects.get(0).getId());
            }
            attendances = attendanceDao.findAllByPupilID(pupil.getId());
            academicPerformances = academicPerformanceDao.findAllByPupilID(pupil.getId());
        }
        System.out.println("===================");
        System.out.println(attendances);
        System.out.println(academicPerformances);
        List<DiaryDTO> diaryDtoList = new ArrayList<>();

        for (Schedule schedule : schedules) {
            diaryDtoList.add(getAttendanceAndGrade(pupil, academicPerformances, subjects, schedule, attendances));
        }

        return diaryDtoList;
    }

    @Override
    public int getNumbAttendance(Long id, Boolean flag) {
        Long pupilId;
        if (flag) {
            pupilId = id;
        } else {
            pupilId = pupilDao.findByUserId(id).getId();
        }
        List<Attendance> attendance = attendanceDao.findAllByPupilID(pupilId);
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

        for (Subject subject : subjects) {
            if (Objects.equals(schedule.getSubjectID(), subject.getId())) {
                if (academicPerformances != null) {
                    for (AcademicPerfomance academicPerfomance : academicPerformances) {
                        if (Objects.equals(academicPerfomance.getLessonID(), schedule.getId())) {
                            if (attendances != null) {
                                if (attendances.stream().anyMatch(it -> Objects.equals(it.getLessonID(), schedule.getId()))) {
                                    return DiaryMapper.mapToDiaryDTO(schedule, pupil, classroomRepository.getById(pupil.getClassroomId()), true, String.valueOf(academicPerfomance.getGrade()), subject);
                                } else {
                                    return DiaryMapper.mapToDiaryDTO(schedule, pupil, classroomRepository.getById(pupil.getClassroomId()), false, String.valueOf(academicPerfomance.getGrade()), subject);
                                }
                            }
                        }
                    }
                }
                if (attendances != null) {
                    if(attendances.stream().anyMatch(it -> Objects.equals(it.getLessonID(), schedule.getId())))
                    {
                        return DiaryMapper.mapToDiaryDTO(schedule, pupil, classroomRepository.getById(pupil.getClassroomId()), true, "", subject);
                    }
                }
                diaryDTO = DiaryMapper.mapToDiaryDTO(schedule, pupil, classroomRepository.getById(pupil.getClassroomId()), false, "", subject);
            }
        }

        return diaryDTO;
    }

    // TODO: change on selected subject
    @Override
    public double getAverageGrade(Long id, Boolean flag, Long classId, int sem) {
        System.out.println(id + " " + flag + " " + classId);
        Long pupilId;
        Pupil pupil;
        if (flag) {
            pupilId = id;
        } else {
            pupil = pupilRepository.findByUserId(id);
            pupilId = pupil.getId();
            classId = pupil.getClassroomId();
        }
        List<AcademicPerfomance> academicPerformanceList = academicPerformanceDao.findAllByPupilIDAndClassroomId(pupilId, classId, sem);

        System.out.println("++++++++" + academicPerformanceList);
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
    public int getSemesterGrade(double grade) {
        String[] a = String.valueOf(grade).split("[.]");
        int _int = Integer.parseInt(a[0]);
        int _franc = Integer.parseInt(String.valueOf(a[1].charAt(0)));
        if (_franc >= 5) {
            return ++_int;
        } else {
            return _int;
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
    public ResponseEntity<?> getDiariesByClassAndSubject(String classname, String subjectCode) {
        List<DiaryBySubjectDTOList> diaryBySubjectDTOList = new ArrayList<>();
        Classroom classroom = classroomDao.findClassroomByName(classname);
        if (classroom == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Такого класса нет"));
        }

        List<Pupil> pupilList = pupilDao.findAllByClassroomId(classroom.getId());
        List<Schedule> schedules = scheduleDao.findAllByClassroomID(classroom.getId());
        Subject subject = subjectDao.findBySubjectCode(subjectCode);

        for (Pupil pupil : pupilList) {
            List<Attendance> attendances = attendanceDao.findAllByPupilID(pupil.getId());
            List<AcademicPerfomance> academicPerformances = academicPerformanceDao.findAllByPupilID(pupil.getId());
            System.out.println(academicPerformances);
            double avGrade = getAverageGrade(pupil.getId(), true, pupil.getClassroomId(), 1); // TODO: поменять, брать с фронта или проверять по датам
            diaryBySubjectDTOList.add(new DiaryBySubjectDTOList(pupil.getName(), pupil.getLastname(), pupil.getPatronymic(), pupil.getCode(), getDiaries(pupil, academicPerformances, subject, schedules, attendances), avGrade, getNumbAttendance(pupil.getId(), true), getSemesterGrade(avGrade)));
        }

        return ResponseEntity.ok(Objects.requireNonNullElse(new DiaryBySubjectDTOResponse(subject.getSubjectName(), classname, diaryBySubjectDTOList), ""));
    }

    private Collection<DiaryBySubjectDTO> getDiaries(Pupil pupil, List<AcademicPerfomance> academicPerformances, Subject subject, List<Schedule> schedules, List<Attendance> attendances) {
        Collection<DiaryBySubjectDTO> diaryDTO = new ArrayList<>();
        String grade;

        for (Schedule schedule : schedules) {
            if (Objects.equals(schedule.getSubjectID(), subject.getId())) {
                if (academicPerformances == null) {
                    if (attendances == null) {
                        System.out.println("1");
                        grade = "0";
                        diaryDTO.add(DiaryMapper.mapToDiaryBySubjectDTO(schedule, false, Integer.parseInt(grade)));
                    } else {
                        System.out.println("2");
                        grade = "0";
                        diaryDTO.add(DiaryMapper.mapToDiaryBySubjectDTO(schedule, true, Integer.parseInt(grade)));
                    }
                } else {
                    for (AcademicPerfomance academicPerfomance : academicPerformances) {
                        if (Objects.equals(academicPerfomance.getLessonID(), schedule.getId()) && academicPerfomance.getPupilID().equals(pupil.getId())) {
                            grade = String.valueOf(academicPerfomance.getGrade());
                            diaryDTO.add(DiaryMapper.mapToDiaryBySubjectDTO(schedule, false, Integer.parseInt(grade)));
                        } else {
                            if (!attendances.isEmpty()) {
                                System.out.println(attendances);
                                for (Attendance attendance : attendances) {
                                    if (attendance.getPupilID().equals(pupil.getId()) && Objects.equals(schedule.getId(), attendance.getLessonID()) && diaryDTO.stream().noneMatch(it -> Objects.equals(it.getScheduleCode(), schedule.getCode()))) {
                                        grade = "0";
                                        diaryDTO.add(DiaryMapper.mapToDiaryBySubjectDTO(schedule, true, Integer.parseInt(grade)));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        schedules.forEach(schedule -> {
            if (diaryDTO.stream().noneMatch(it -> Objects.equals(it.getScheduleCode(), schedule.getCode()))) {
                diaryDTO.add(DiaryMapper.mapToDiaryBySubjectDTO(schedule, false, 0));
            }
        });

        return diaryDTO;
    }
}