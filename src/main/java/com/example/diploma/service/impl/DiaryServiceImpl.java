package com.example.diploma.service.impl;

import com.example.diploma.dto.DiaryDTO;
import com.example.diploma.model.*;
//import com.example.diploma.mapper.Mapper;
import com.example.diploma.service.DiaryService;
import com.example.diploma.repo.*;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
/*
    private final AttendanceRepository attendanceRepository;

    private final AcademicPerfomanceRepository academicPerfomanceRepository;

    private final PupilRepository pupilRepository;

    private final SheduleRepository sheduleRepository;

    private final SubjectRepository subjectRepository;

    private final ClassroomRepository classroomRepository;

    @Override
    public DiaryDTO addAcademicPerfomance(DiaryDTO diaryDTO) {
        AcademicPerfomance academicPerfomance = new AcademicPerfomance();
        Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil());
        Subject subject = subjectRepository.findBySubjectName(diaryDTO.getSubject());

        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика");
            return diaryDTO;
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета");
            return diaryDTO;
        }

        Shedule shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId(), subject.getId());

        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате");
            return diaryDTO;
        }

        academicPerfomance.setClassID(pupil.getClassroomId());
        academicPerfomance.setLessonID(shedule.getId());
        academicPerfomance.setPupilID(pupil.getId());
        academicPerfomance.setGrade(Integer.parseInt(diaryDTO.getGrade()));
        academicPerfomanceRepository.save(academicPerfomance);

        diaryDTO.setNamePupil("ок");
        return diaryDTO;
    }

    @Override
    public DiaryDTO addAttendance(DiaryDTO diaryDTO) {
        Attendance attendance = new Attendance();
        Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil());
        Subject subject = subjectRepository.findBySubjectName(diaryDTO.getSubject());

        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика");
            return diaryDTO;
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета");
            return diaryDTO;
        }

        Shedule shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId(), subject.getId());

        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате");
            return diaryDTO;
        }

        attendance.setPupilID(pupil.getId());
        attendance.setClassID(pupil.getClassroomId());
        attendance.setLessonID(shedule.getId());
        attendanceRepository.save(attendance);

        diaryDTO.setNamePupil("ок");
        return diaryDTO;
    }

    @Override
    public DiaryDTO addSubject(DiaryDTO diaryDTO) {
        Subject subject = subjectRepository.findBySubjectName(diaryDTO.getSubject());
        Classroom classroom = classroomRepository.findClassroomByName(diaryDTO.getClassName());

        if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в предмете");
            return diaryDTO;
        } else if (classroom == null) {
            diaryDTO.setNamePupil("Ошибка в названии класса");
            return diaryDTO;
        }

        Shedule shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), classroom.getId(), subject.getId());

        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате предмета");
            return diaryDTO;
        }

        shedule.setHometask(diaryDTO.getHomework());
        sheduleRepository.save(shedule);

        return diaryDTO;
    }

    @Override
    public boolean getAttendance(DiaryDTO diaryDTO) {
        Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil());
        Subject subject = subjectRepository.findBySubjectName(diaryDTO.getSubject());
        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика");
            return false;
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета");
            return false;
        }
        Shedule shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId(), subject.getId());
        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате");
            return false;
        }
        return attendanceRepository.existsByClassIDAndLessonIDAndPupilID(pupil.getClassroomId(), shedule.getId(), pupil.getId());
    }

    @Override
    public boolean getAcademicPerfomance(DiaryDTO diaryDTO) {
        System.out.println("в проверке" + diaryDTO);
        Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(diaryDTO.getNamePupil(), diaryDTO.getLastnamePupil(), diaryDTO.getPatronymicPupil());
        Subject subject = subjectRepository.findBySubjectName(diaryDTO.getSubject());

        if (pupil == null) {
            diaryDTO.setNamePupil("Ошибка в ФИО ученика");
            return false;
        } else if (subject == null) {
            diaryDTO.setNamePupil("Ошибка в названии предмета");
            return false;
        }

        Shedule shedule = sheduleRepository.findByDateAndClassroomIDAndSubjectID(diaryDTO.getDateLesson(), pupil.getClassroomId(), subject.getId());

        if (shedule == null) {
            diaryDTO.setNamePupil("Ошибка в дате");
            return false;
        }
        return academicPerfomanceRepository.existsByClassIDAndLessonIDAndPupilID(pupil.getClassroomId(), shedule.getId(), pupil.getId());
    }

    @Override
    public List<DiaryDTO> getDiaryDTOByUser(Long id) {
        Pupil pupil = pupilRepository.findByUserId(id);
        List<Shedule> shedules = sheduleRepository.findAllByClassroomID(pupil.getClassroomId());
        List<Subject> subjects = subjectRepository.findAll();
        List<Attendance> attendances = attendanceRepository.findAllByPupilID(pupil.getId());
        List<AcademicPerfomance> academicPerfomances = academicPerfomanceRepository.findAllByPupilID(pupil.getId());

        List<DiaryDTO> diaryDTOList = new ArrayList<>();

        boolean attendanceBoolean = false;
        String grade = "";

        for (Shedule shedule : shedules) {
            DiaryDTO diaryDTO = new DiaryDTO();
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
                    diaryDTO = Mapper.mapToDiaryDTO(shedule, pupil, classroomRepository.getById(pupil.getClassroomId()), attendanceBoolean, grade, subject);
                }
            }
            diaryDTOList.add(diaryDTO);
        }

        return diaryDTOList;
    }

    @Override
    public int getNumbAttendance(Long id) {
        Pupil pupil = pupilRepository.findByUserId(id);
        List<Attendance> attendance = attendanceRepository.findAllByPupilID(pupil.getId());
        return attendance.size();
    }

    // TODO: упростить, как в scheduleController
    @Override
    public List<DiaryDTO> getDiaryDTOByClass(String classForSearch) {
        List<DiaryDTO> diaryDTOList = new ArrayList<>();
        DiaryDTO diaryDTO = new DiaryDTO();
        Classroom classroom = classroomRepository.findClassroomByName(classForSearch);
        if (classroom == null) {
            diaryDTO.setNamePupil("Такого класса нет");
            diaryDTOList.add(diaryDTO);
            return diaryDTOList;
        }

        List<Pupil> pupilList = pupilRepository.findAllByClassroomId(classroom.getId());
        List<Shedule> shedules = sheduleRepository.findAllByClassroomID(classroom.getId());
        List<Subject> subjects = subjectRepository.findAll();

        boolean attendanceBoolean = false;
        String grade = "";

        for (Pupil pupil : pupilList) {
            List<Attendance> attendances = attendanceRepository.findAllByPupilID(pupil.getId());
            List<AcademicPerfomance> academicPerfomances = academicPerfomanceRepository.findAllByPupilID(pupil.getId());
            for (Shedule shedule : shedules) {
                diaryDTO = new DiaryDTO();
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
                        diaryDTO = Mapper.mapToDiaryDTO(shedule, pupil, classroomRepository.getById(pupil.getClassroomId()), attendanceBoolean, grade, subject);
                    }
                }
                diaryDTOList.add(diaryDTO);
            }
        }

        return diaryDTOList;
    }

    @Override
    public double getAverageGrade(Long id) {
        Pupil pupil = pupilRepository.findByUserId(id);
        List<AcademicPerfomance> academicPerfomanceList = academicPerfomanceRepository.findAllByPupilID(pupil.getId());

        double sumGrade = 0;
        for (AcademicPerfomance academicPerfomance : academicPerfomanceList) {
            sumGrade += academicPerfomance.getGrade();
        }
        if (sumGrade != 0) {
            return sumGrade / academicPerfomanceList.size();
        } else {
            return 0;
        }
    }

    @Override
    public void saveGradesByUserId(Long userId) {
        List<DiaryDTO> diaryDTOList = getDiaryDTOByUser(userId);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
            objectWriter.writeValue(new File("D:\\BSUIR\\6semestr\\CourseWork\\Programm\\diploma\\server\\src\\main\\resources\\" + diaryDTOList.get(0).getLastnamePupil() + "diary.json"), diaryDTOList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
}