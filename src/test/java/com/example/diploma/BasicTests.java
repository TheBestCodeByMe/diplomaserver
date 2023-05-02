package com.example.diploma;

import com.example.diploma.dao.*;
import com.example.diploma.dto.classroom.ClassroomDTO;
import com.example.diploma.dto.pupil.CreatePupilDTORequest;
import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.dto.schedule.CreateScheduleDTORequest;
import com.example.diploma.dto.subject.CreateSubjectDTORequest;
import com.example.diploma.dto.subject.SubjectDTO;
import com.example.diploma.dto.teacher.CreateTeacherDTORequest;
import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.mapper.PupilMapper;
import com.example.diploma.mapper.TeacherMapper;
import com.example.diploma.model.Calendar;
import com.example.diploma.model.Pupil;
import com.example.diploma.model.Subject;
import com.example.diploma.model.Teacher;
import com.example.diploma.repo.CalendarRepository;
import com.example.diploma.repo.PupilRepository;
import com.example.diploma.repo.SubjectRepository;
import com.example.diploma.service.PupilService;
import com.example.diploma.service.impl.EditUsersServiceImpl;
import com.example.diploma.service.impl.EmployeeServiceImpl;
import com.example.diploma.service.impl.PupilServiceImpl;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@SpringBootTest
@Disabled
class BasicTests {

    static {
        System.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/test");
    }

    ClassroomDTO classroomDTO;
    CreatePupilDTORequest createPupilDTORequest;
    com.example.diploma.model.Pupil pupil;
    CreateTeacherDTORequest createTeacherDTORequest;
    Teacher teacher;
    CreateSubjectDTORequest createSubjectDTORequest;
    Calendar calendar;
    CreateScheduleDTORequest createScheduleDTORequest;

    @BeforeEach
    void init() {
        classroomDTO = new ClassroomDTO("11 А", "name", "lastname", "patronymic");
        createPupilDTORequest = new CreatePupilDTORequest("namePup", "lastnamePup", "patronymicPup", LocalDate.now(), "email", "personalCheck", "nameMom", "lastnameMom", "patronymicMom", "nameDad", "lastnameDad", "patronymicDad", "11 А");
        pupil = PupilMapper.mapPupilDTOToPupil(createPupilDTORequest, "0");
        createTeacherDTORequest = new CreateTeacherDTORequest("name", "lastname", "patronymic", "email", "qualification", "position");
        teacher = TeacherMapper.mapDtoToTeacher(createTeacherDTORequest, "0");
        createSubjectDTORequest = new CreateSubjectDTORequest("math");
        calendar = new Calendar(1, 1, 1);
        createScheduleDTORequest = new CreateScheduleDTORequest(classroomDTO.getName(), createTeacherDTORequest.getName(), createTeacherDTORequest.getLastName(), createTeacherDTORequest.getPatronymic(), createSubjectDTORequest.getName(), calendar.getWeekDay(), calendar.getSemesterID(), LocalDate.now(), calendar.getLessonNumber(), "Paragraph 77165");
    }

    @Autowired
    EditUsersServiceImpl editUsersService;

    @Autowired
    EmployeeServiceImpl employeeService;

    @Order(1)
    @Test
    void createTeacher() {
        if (employeeService.getTeacherByFIO(teacher) == null) {
            ResponseEntity<TeacherDTO> teacherDTO = (ResponseEntity<TeacherDTO>) editUsersService.createTeacher(createTeacherDTORequest);
            Assertions.assertEquals("qualification", Objects.requireNonNull(teacherDTO.getBody()).getQualification());
        }
    }

    @Order(2)
    @Test
    void getTeacher() {
        List<TeacherDTO> teacherDTOList = employeeService.getTeacherByFIO(teacher);
        Assertions.assertEquals(teacher.getName(), teacherDTOList.get(0).getName());
    }

    @Order(3)
    @Test
    void getAllTeacher() {
        List<TeacherDTO> teacherDTOList = employeeService.getAllTeacher();
        Assertions.assertTrue(teacherDTOList.size() != 0);
    }

    @Order(4)
    @Test
    void getTeacherByUserId() {
        TeacherDTO teacherDTO = employeeService.getTeacherByUserId(String.valueOf(teacher.getUserId()));
        Assertions.assertEquals(teacher.getName(), teacherDTO.getName());
    }

    @Autowired
    PupilDao pupilDao;
    @Autowired
    ClassroomDao classroomDao;


    @Order(5)
    @Test
    void createClassroom() {
        if (classroomDao.findClassroomByName(classroomDTO.getName()) == null) {
            ResponseEntity<ClassroomDTO> classroomDTOResponseEntity = (ResponseEntity<ClassroomDTO>) editUsersService.createClassroom(classroomDTO);
            Assertions.assertEquals(classroomDTO.getName(), Objects.requireNonNull(classroomDTOResponseEntity.getBody().getName()));
        }
    }

    @Order(6)
    @Test
    void createPupil() {
        if (pupilDao.findByFio(createPupilDTORequest.getName(), createPupilDTORequest.getLastname(), createPupilDTORequest.getPatronymic()) == null) {
            ResponseEntity<PupilDTO> pupilDTOResponseEntity = (ResponseEntity<PupilDTO>) editUsersService.createPupil(createPupilDTORequest);
            Assertions.assertEquals(createPupilDTORequest.getPersonalCheck(), Objects.requireNonNull(pupilDTOResponseEntity.getBody()).getPersonalCheck());
        }
    }

    @Autowired
    PupilServiceImpl pupilService;

    @Order(7)
    @Test
    void getPupilByUserId() {
        PupilDTO pupilDTO = pupilService.getPupilByUserId("2");
        Assertions.assertEquals(createPupilDTORequest.getName(), pupilDTO.getName());
    }

    @Order(8)
    @Test
    void getClassroom() {
        Assertions.assertEquals(classroomDTO.getName(),
                classroomDao.findClassroomByName(classroomDTO.getName()).getName());
    }

    @Autowired
    TeacherDao teacherDao;

    @Order(9)
    @Test
    void getClassroomByTeacher() {
        Long classroomTeacherId = classroomDao.findClassroomByName(classroomDTO.getName()).getClassroomTeacherId();
        Teacher teacherRes = teacherDao.findByFio(classroomDTO.getClassroomTeacherName(), classroomDTO.getClassroomTeacherLastname(), classroomDTO.getClassroomTeacherPatronymic());
        Assertions.assertEquals(classroomTeacherId, teacherRes.getId());
    }

    @Autowired
    SubjectDao subjectDao;

    @Order(10)
    @Test
    void createSubject() {
        if (subjectDao.findBySubjectName(createSubjectDTORequest.getName()) == null) {
            ResponseEntity<SubjectDTO> subjectDTOResponseEntity = (ResponseEntity<SubjectDTO>) editUsersService.createSubject(createSubjectDTORequest);
            Assertions.assertEquals(createSubjectDTORequest.getName(), Objects.requireNonNull(subjectDTOResponseEntity.getBody().getName()));
        }
    }

    @Order(11)
    @Test
    void getSubject() {
        Subject subjectResponse = subjectDao.findBySubjectName(createSubjectDTORequest.getName());
        Assertions.assertNotNull(subjectResponse);
    }

    @Autowired
    CalendarDao calendarDao;

    @Order(11)
    @Test
    void getCalendar() {
        Calendar calendarRes = calendarDao.findByLessonNumberAndWeekDay(calendar.getLessonNumber(), calendar.getWeekDay());
        Assertions.assertNotNull(calendarRes);
    }

    @Order(12)
    @Test
    void createSchedule() {
        if (subjectDao.findBySubjectName(createSubjectDTORequest.getName()) == null) {
            ResponseEntity<SubjectDTO> subjectDTOResponseEntity = (ResponseEntity<SubjectDTO>) editUsersService.createSubject(createSubjectDTORequest);
            Assertions.assertEquals(createSubjectDTORequest.getName(), Objects.requireNonNull(subjectDTOResponseEntity.getBody().getName()));
        }
    }

    @Order(13)
    @Test
    void getSchedule() {
        Subject subjectResponse = subjectDao.findBySubjectName(createSubjectDTORequest.getName());
        Assertions.assertNotNull(subjectResponse);
    }
}
