package com.example.diploma.service.impl;

import com.example.diploma.dto.ClassroomDTO;
import com.example.diploma.dto.PupilDTO;
import com.example.diploma.dto.SheduleDTO;
import com.example.diploma.model.*;
import com.example.diploma.model.Pupil;
import com.example.diploma.exception.ResourceNotFoundException;
import com.example.diploma.mapper.Mapper;
import com.example.diploma.service.EditUsersService;
import com.example.diploma.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EditUsersServiceImpl implements EditUsersService {

    private final UserRepository userRepository;

    private final PupilRepository pupilRepository;

    private final ParentsRepository parentsRepository;

    private final ClassroomRepository classroomRepository;

    private final TeacherRepository teacherRepository;

    private final SubjectRepository subjectRepository;

    private final CalendarRepository calendarRepository;

    private final SheduleRepository sheduleRepository;
/*
    @Override
    public Pupil createPupil(PupilDTO pupilDTO) {
        Pupil pupil = Mapper.mapPupilDTOToPupil(pupilDTO);
        Parents parents = Mapper.mapPupilDTOToParents(pupilDTO);
        Classroom classroom = Mapper.mapPupilDTOToClassroom(pupilDTO);
        Classroom classroom1 = classroomRepository.findClassroomByName(classroom.getName());

        if (classroom1 != null) {
            pupil.setClassroomId(classroom1.getId());

            Parents parent = parentsRepository.findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(parents.getNameDad(), parents.getLastnameDad(), parents.getPatronymicDad(), parents.getNameMom(), parents.getLastnameMom(), parents.getPatronymicMom());

            if (parent != null) {
                pupil.setParentsId(parent.getId());
            } else {
                parentsRepository.save(parents);
                Parents newParents = parentsRepository.findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(parents.getNameDad(), parents.getLastnameDad(), parents.getPatronymicDad(), parents.getNameMom(), parents.getLastnameMom(), parents.getPatronymicMom());
                pupil.setParentsId(newParents.getId());
            }
            return pupilRepository.save(pupil);
        }

        Pupil pupil1 = new Pupil();
        return pupilRepository.save(pupil1);
    }

    @Override
    public List<PupilDTO> getAllPupilDTO() {
        List<Pupil> pupils = pupilRepository.findAll();
        List<Parents> parents = parentsRepository.findAll();
        List<Classroom> classrooms = classroomRepository.findAll();

        List<PupilDTO> pupilDTOS = new ArrayList<>();

        for (Pupil pupil : pupils) {
            for (Parents parent : parents) {
                if (pupil.getParentsId() == parent.getId()) {
                    for (Classroom classroom : classrooms) {
                        if (pupil.getClassroomId() == classroom.getId()) {
                            Mapper.mapToPupilDTO(pupil, parent, classroom);
                        }
                    }
                }
            }
        }

        return pupilDTOS;//userRepository.findAll();
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        if (teacherRepository.findByNameAndLastNameAndPatronymic(teacher.getName(), teacher.getLastName(), teacher.getPatronymic()) == null) {
            teacherRepository.save(teacher);
            return teacher;
        }
        return null;
    }

    @Override
    public Subject createSubject(Subject subject) {
        if (subjectRepository.findBySubjectName(subject.getSubjectName()) == null) {
            subjectRepository.save(subject);
            return subject;
        }
        subject.setSubjectName("Такой предмет уже есть");
        return subject;
    }

    @Override
    public SheduleDTO createSheduleDTO(SheduleDTO sheduleDTO) {
        Classroom classroom = classroomRepository.findClassroomByName(sheduleDTO.getClassroomName());
        Subject subject = subjectRepository.findBySubjectName(sheduleDTO.getSubjectName());
        Teacher teacher = teacherRepository.findByNameAndLastNameAndPatronymic(sheduleDTO.getNameTeacher(), sheduleDTO.getLastnameTeacher(), sheduleDTO.getPatronymicTeacher());
        Calendar calendar = calendarRepository.findByLessonNumberAndWeekDay(sheduleDTO.getLessonNumber(), sheduleDTO.getWeekDay());

        if (classroom == null) {
            sheduleDTO.setSubjectName("Такого класса не существует");
        } else if (subject == null) {
            sheduleDTO.setSubjectName("Такого предмета не существует");
        } else if (teacher == null) {
            sheduleDTO.setSubjectName("Такого преподавателя не существует");
        } else if (calendar == null) {
            sheduleDTO.setSubjectName("Такого времени урока не существует");
        } else {
            Shedule shedule = Mapper.mapSheduleDTOToShedule(sheduleDTO, calendar.getId(), teacher.getId(), subject.getId(), classroom.getId());

            if (sheduleRepository.findByCalendarIdAndClassroomIDAndDateAndSubjectIDAndTeacherIDAndWeekDay(shedule.getCalendarId(), shedule.getClassroomID(), shedule.getDate(), shedule.getSubjectID(), shedule.getTeacherID(), shedule.getWeekDay()) != null) {
                sheduleDTO.setSubjectName("Такое расписание уже есть");
            } else if (sheduleRepository.findByTeacherIDAndCalendarIdAndDate(shedule.getTeacherID(), shedule.getCalendarId(), shedule.getDate()) != null) {
                sheduleDTO.setSubjectName("Учитель занят в это время");
            } else if (sheduleRepository.findByCalendarIdAndClassroomIDAndDate(shedule.getCalendarId(), shedule.getClassroomID(), shedule.getDate()) != null) {
                sheduleDTO.setSubjectName("У класса уже есть занятие в это время");
            } else {
                sheduleRepository.save(shedule);
            }
        }
        return sheduleDTO;
    }

    @Override
    public ClassroomDTO createClassroom(ClassroomDTO classroomDTO) {
        Teacher teacher = teacherRepository.findByNameAndLastNameAndPatronymic(classroomDTO.getClassroomTeacherName(), classroomDTO.getClassroomTeacherLastname(), classroomDTO.getClassroomTeacherPatronymic());

        if (classroomRepository.findClassroomByName(classroomDTO.getName()) != null) {
            classroomDTO.setName("Такой класс уже есть");
        } else if (teacher == null) {
            classroomDTO.setName("Такого преподавателя нет");
        } else {
            Classroom classroom = Mapper.mapClassroomDTOToClassroom(classroomDTO, teacher.getId());
            classroomRepository.save(classroom);
        }
        return classroomDTO;
    }

    @Override
    public Map<String, Boolean> deleteUser(String login)
            throws ResourceNotFoundException {
        User user = userRepository.findByLogin(login).orElseThrow(null);
        Map<String, Boolean> response = new HashMap<>();
        if (user != null) {
            userRepository.delete(user);
            response.put("deleted", Boolean.TRUE);
            Pupil pupil = pupilRepository.findByUserId(user.getId());
            Teacher teacher = teacherRepository.findByUserId(user.getId());
            if (pupil != null) {
                pupil.setUserId(0);
                pupilRepository.save(pupil);
            } else if (teacher != null) {
                teacher.setUserId(0);
                teacherRepository.save(teacher);
            }
        } else {
            response.put("notDeleted", Boolean.FALSE);
        }
        return response;
    }

    @Override
    public Map<String, Boolean> blockUser(String login)
            throws ResourceNotFoundException {
        User user = userRepository.findByLogin(login).orElseThrow(null);
        Map<String, Boolean> response = new HashMap<>();
        if (user != null) {
            user.setStatus("block");
            userRepository.save(user);
            response.put("blocked", Boolean.TRUE);
        } else {
            response.put("notBlocked", Boolean.FALSE);
        }
        return response;
    }

    @Override
    public Map<String, Boolean> unblockUser(String login) {
        User user = userRepository.findByLogin(login).orElseThrow(null);
        Map<String, Boolean> response = new HashMap<>();
        if (user != null) {
            user.setStatus("unBlock");
            userRepository.save(user);
            response.put("unBlocked", Boolean.TRUE);
        } else {
            response.put("notUnBlocked", Boolean.FALSE);
        }
        return response;
    }*/
}