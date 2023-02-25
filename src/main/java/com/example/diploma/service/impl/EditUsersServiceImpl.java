package com.example.diploma.service.impl;

import com.example.diploma.dao.*;
import com.example.diploma.dto.classroom.ClassroomDTO;
import com.example.diploma.dto.pupil.CreatePupilDTORequest;
import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.dto.schedule.CreateScheduleDTORequest;
import com.example.diploma.dto.subject.CreateSubjectDTORequest;
import com.example.diploma.dto.teacher.CreateTeacherDTORequest;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.exception.ResourceNotFoundException;
import com.example.diploma.mapper.*;
import com.example.diploma.model.*;
import com.example.diploma.model.Calendar;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.service.EditUsersService;
import com.example.diploma.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EditUsersServiceImpl implements EditUsersService {

    private final UserRepository userRepository;
    private final UserDao userDao;

    private final PupilRepository pupilRepository;
    private final PupilDao pupilDao;

    private final ParentsRepository parentsRepository;
    private final ParentsDao parentsDao;

    private final ClassroomRepository classroomRepository;
    private final ClassroomDao classroomDao;

    private final TeacherRepository teacherRepository;
    private final TeacherDao teacherDao;

    private final SubjectRepository subjectRepository;
    private final SubjectDao subjectDao;

    private final CalendarRepository calendarRepository;
    private final CalendarDao calendarDao;

    private final ScheduleRepository scheduleRepository;
    private final ScheduleDao scheduleDao;

    @Override
    public ResponseEntity<?> createPupil(CreatePupilDTORequest createPupilDTORequest) {
        Pupil pupil = PupilMapper.mapPupilDTOToPupil(createPupilDTORequest, GenerationCodeServiceImpl.generateCode());
        Parents parents = ParentsMapper.mapPupilDTOToParents(createPupilDTORequest, GenerationCodeServiceImpl.generateCode());
        Classroom classroom = ClassroomMapper.mapCreatePupilDTOToClassroom(createPupilDTORequest);
        Classroom classroomFromDB = classroomDao.findClassroomByName(classroom.getName());

        if (pupilDao.findByFio(pupil.getName(), pupil.getLastname(), pupil.getPatronymic()) != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Такой учащийся уже существует"));
        } else {

            if (classroomFromDB != null) {
                pupil.setClassroomId(classroomFromDB.getId());

                Parents parent = parentsDao.findByFIO(parents.getNameDad(), parents.getLastnameDad(), parents.getPatronymicDad(), parents.getNameMom(), parents.getLastnameMom(), parents.getPatronymicMom());

                if (parent != null) {
                    pupil.setParentsId(parent.getId());
                } else {
                    parentsRepository.save(parents);
                    Parents newParents = parentsDao.findByCode(parents.getCode());
                    pupil.setParentsId(newParents.getId());
                }
            } else {
                return ResponseEntity.badRequest().body("Error: Такого класса не существует");
            }

            return ResponseEntity.ok(PupilMapper.mapToPupilDTO(pupilRepository.save(pupil), parents, classroom));
        }
    }

    @Override
    public List<PupilDTO> getAllPupilDTO() {
        List<Pupil> pupils = pupilDao.findAll();
        List<Parents> parents = parentsDao.findAll();
        List<Classroom> classrooms = classroomDao.findAll();

        List<PupilDTO> pupilDTOS = new ArrayList<>();

        for (Pupil pupil : pupils) {
            for (Parents parent : parents) {
                if (pupil.getParentsId() == parent.getId()) {
                    for (Classroom classroom : classrooms) {
                        if (pupil.getClassroomId() == classroom.getId()) {
                            pupilDTOS.add(PupilMapper.mapToPupilDTO(pupil, parent, classroom));
                        }
                    }
                }
            }
        }

        return pupilDTOS;//userRepository.findAll();
    }

    @Override
    public ResponseEntity<?> createTeacher(CreateTeacherDTORequest teacherDTO) {
        if (teacherRepository.findByNameAndLastNameAndPatronymic(teacherDTO.getName(), teacherDTO.getLastName(), teacherDTO.getPatronymic()) == null) {
            Teacher teacher = teacherRepository.save(TeacherMapper.mapDtoToTeacher(teacherDTO, GenerationCodeServiceImpl.generateCode()));
            return ResponseEntity.ok(TeacherMapper.mapToTeacherDto(teacher));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Такой учитель уже существует"));
    }

    @Override
    public ResponseEntity<?> createSubject(CreateSubjectDTORequest createSubjectDTORequest) {
        if (subjectDao.findBySubjectName(createSubjectDTORequest.getName().toLowerCase()) == null) {
            Subject subject = subjectRepository.save(SubjectMapper.mapSubjectDTOToSubject(createSubjectDTORequest, GenerationCodeServiceImpl.generateCode()));
            return ResponseEntity.ok(SubjectMapper.mapSubjectToSubjectDTO(subject));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Такой предмет уже есть"));
    }

    @Override
    public ResponseEntity<?> createScheduleDTO(CreateScheduleDTORequest createScheduleDTORequest) {
        Classroom classroom = classroomDao.findClassroomByName(createScheduleDTORequest.getClassroomName());
        Subject subject = subjectDao.findBySubjectName(createScheduleDTORequest.getSubjectName());
        Teacher teacher = teacherDao.findByFio(createScheduleDTORequest.getNameTeacher(), createScheduleDTORequest.getLastnameTeacher(), createScheduleDTORequest.getPatronymicTeacher());
        Calendar calendar = calendarDao.findByLessonNumberAndWeekDay(createScheduleDTORequest.getLessonNumber(), createScheduleDTORequest.getWeekDay());

        if (classroom == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Такого класса не существует: " + createScheduleDTORequest.getClassroomName()));
        } else if (subject == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Такого предмета не существует"));
        } else if (teacher == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Такого преподавателя не существует"));
        } else if (calendar == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Такого времени урока не существует"));
        } else {
            Schedule schedule = ScheduleMapper.mapScheduleDTOToSchedule(createScheduleDTORequest, calendar.getId(), teacher.getId(), subject.getId(), classroom.getId(), GenerationCodeServiceImpl.generateCode());

         if (scheduleDao.findSchedule(schedule.getCalendarId(), schedule.getClassroomID(), schedule.getDate(), schedule.getSubjectID(), schedule.getTeacherID(), schedule.getWeekDay()) != null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Такое расписание уже есть"));
            } else if (scheduleDao.findForTeacher(schedule.getTeacherID(), schedule.getCalendarId(), schedule.getDate()) != null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Учитель занят в это время"));
            } else if (scheduleDao.findForClassroom(schedule.getCalendarId(), schedule.getClassroomID(), schedule.getDate()) != null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: У класса уже есть занятие в это время"));
            } else {
                return ResponseEntity.ok(ScheduleMapper.mapScheduleToScheduleDTO(scheduleRepository.save(schedule), calendar, subject, teacher, classroom));
            }
        }
    }

    @Override
    public ResponseEntity<?> createClassroom(ClassroomDTO classroomDTO) {
        Teacher teacher = teacherDao.findByFio(classroomDTO.getClassroomTeacherName(), classroomDTO.getClassroomTeacherLastname(), classroomDTO.getClassroomTeacherPatronymic());

        if (classroomDao.findClassroomByName(classroomDTO.getName()) != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Такой класс уже есть"));
            //classroomDTO.setName("Такой класс уже есть");
        } else if (teacher == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Такого преподавателя нет"));
            //classroomDTO.setName("Такого преподавателя нет");
        } else if (classroomDao.findClassroomByTeacherId(teacher.getId())!=null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: У учителя уже есть класс"));
        } else{
            Classroom classroom = ClassroomMapper.mapClassroomDTOToClassroom(classroomDTO, teacher.getId(), GenerationCodeServiceImpl.generateCode());
            classroomRepository.save(classroom);
        }
        return ResponseEntity.ok(classroomDTO);
    }

    @Override
    public boolean deleteUser(String login) {
        User user = userDao.findByLogin(login);
        boolean response = false;
        if (user != null) {
            user.setStatus(EStatus.CLOSED.getId());
            userRepository.save(user);
            response = true;
            Pupil pupil = pupilDao.findByUserId(user.getId());
            Teacher teacher = teacherDao.findByUserId(user.getId());
            if (pupil != null) {
                pupil.setUserId(2);
                pupilRepository.save(pupil);
            } else if (teacher != null) {
                teacher.setUserId(2);
                teacherRepository.save(teacher);
            }
        }
        return response;
    }

    @Override
    public boolean blockUser(String login) {
        User user = userDao.findByLogin(login);
        boolean response = false;
        if (user != null) {
            user.setStatus(EStatus.BANNED.getId());
            userRepository.save(user);
            response = true;
        }
        return response;
    }

    @Override
    public boolean unblockUser(String login) {
        User user = userDao.findByLogin(login);
        boolean response = false;
        if (user != null) {
            user.setStatus(EStatus.ACTIVE.getId());
            userRepository.save(user);
            response = true;
        }
        return response;
    }
}