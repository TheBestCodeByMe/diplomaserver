package com.example.diploma.service.impl;

import com.example.diploma.dao.ClassroomDao;
import com.example.diploma.dao.SubjectDao;
import com.example.diploma.dao.TeacherDao;
import com.example.diploma.dto.classroom.ClassroomDTO;
import com.example.diploma.dto.subject.CreateSubjectDTORequest;
import com.example.diploma.dto.subject.SubjectDTO;
import com.example.diploma.dto.teacher.CreateTeacherDTORequest;
import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.mapper.ClassroomMapper;
import com.example.diploma.mapper.SubjectMapper;
import com.example.diploma.mapper.TeacherMapper;
import com.example.diploma.model.Classroom;
import com.example.diploma.model.Subject;
import com.example.diploma.model.Teacher;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.service.EditUsersService;
import com.example.diploma.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EditUsersServiceImpl implements EditUsersService {

    private final UserRepository userRepository;

    private final PupilRepository pupilRepository;

    private final ParentsRepository parentsRepository;

    private final ClassroomDao classroomDao;

    private final ClassroomRepository classroomRepository;

    private final TeacherRepository teacherRepository;

    private final TeacherDao teacherDao;

    private final SubjectRepository subjectRepository;

    private final SubjectDao subjectDao;

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
*/
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
        if (subjectDao.findBySubjectName(createSubjectDTORequest.getName().toLowerCase(Locale.ROOT)) == null) {
            Subject subject = subjectRepository.save(SubjectMapper.mapSubjectDTOToSubject(createSubjectDTORequest, GenerationCodeServiceImpl.generateCode()));
            return ResponseEntity.ok(SubjectMapper.mapSubjectToSubjectDTO(subject));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Такой предмет уже есть"));
    }
/*
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
*/
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
/*
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