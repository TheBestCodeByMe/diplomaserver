package com.example.diploma.mapper;

import com.example.diploma.dto.*;
import com.example.diploma.model.*;
import com.example.diploma.enumiration.ERole;

import java.util.HashSet;
import java.util.Set;

public class Mapper {
/*    public static PupilDTO mapToPupilDTO(Pupil pupils, Parents parents, Classroom classrooms) {
        PupilDTO pupilDTOS = new PupilDTO();
        pupilDTOS.setName(pupils.getName());
        pupilDTOS.setLastname(pupils.getLastname());
        pupilDTOS.setPatronymic(pupils.getPatronymic());
        pupilDTOS.setEmail(pupils.getEmail());
        pupilDTOS.setDateOfBirthday(pupils.getDateOfBirthday());
        pupilDTOS.setPersonalCheck(pupils.getPersonalCheck());
        pupilDTOS.setClassName(classrooms.getName());
        pupilDTOS.setNameMom(parents.getNameMom());
        pupilDTOS.setNameDad(parents.getNameDad());
        pupilDTOS.setLastnameDad(parents.getLastnameDad());
        pupilDTOS.setPatronymicDad(parents.getPatronymicDad());
        pupilDTOS.setLastnameMom(parents.getLastnameMom());
        pupilDTOS.setPatronymicMom(parents.getPatronymicMom());

        return pupilDTOS;
    }

    public static Pupil mapPupilDTOToPupil(PupilDTO pupilDTOs) {
        Pupil pupil = new Pupil();

        pupil.setName(pupilDTOs.getName());
        pupil.setLastname(pupilDTOs.getLastname());
        pupil.setPatronymic(pupilDTOs.getPatronymic());
        pupil.setEmail(pupilDTOs.getEmail());
        pupil.setDateOfBirthday(pupilDTOs.getDateOfBirthday());
        pupil.setPersonalCheck(pupilDTOs.getPersonalCheck());

        return pupil;
    }

    public static Parents mapPupilDTOToParents(PupilDTO pupilDTOs) {
        Parents parent = new Parents();
        parent.setNameMom(pupilDTOs.getNameMom());
        parent.setNameDad(pupilDTOs.getNameDad());
        parent.setLastnameDad(pupilDTOs.getLastnameDad());
        parent.setPatronymicDad(pupilDTOs.getPatronymicDad());
        parent.setLastnameMom(pupilDTOs.getLastnameMom());
        parent.setPatronymicMom(pupilDTOs.getPatronymicMom());

        return parent;
    }

    public static Classroom mapPupilDTOToClassroom(PupilDTO pupilDTOs) {
        Classroom classroom = new Classroom();
        classroom.setName(pupilDTOs.getClassName());

        return classroom;
    }

    public static User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        // TODO: переделать
        Set<Role> role = new HashSet<>();
        role.add(new Role(ERole.ROLE_PUPIL));
        user.setRoles(role);
        user.setPassword(userDTO.getPassword());
        user.setStatus(userDTO.getStatus());
        user.setLink(userDTO.getLink());

        return user;
    }

    public static Pupil mapUserDTOToPupil(UserDTO userDTO) {
        Pupil pupil = new Pupil();
        pupil.setEmail(userDTO.getEmail());
        pupil.setName(userDTO.getName());
        pupil.setLastname(userDTO.getLastname());
        pupil.setPatronymic(userDTO.getPatronymic());

        return pupil;
    }

    public static UserDTO mapUserToUserDTO(User user, Pupil pupil) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setRole(user.getRoles().toString());
        userDTO.setPassword(user.getPassword());
        userDTO.setStatus(user.getStatus());
        userDTO.setLink(user.getLink());
        userDTO.setEmail(pupil.getEmail());
        userDTO.setLastname(pupil.getLastname());
        userDTO.setName(pupil.getName());
        userDTO.setPatronymic(pupil.getPatronymic());

        return userDTO;
    }

    public static UserDTO mapUserTeacherToUserDTO(User user, Teacher teacher) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(user.getLogin());
        userDTO.setRole(user.getRoles().toString());
        userDTO.setPassword(user.getPassword());
        userDTO.setStatus(user.getStatus());
        userDTO.setLink(user.getLink());
        userDTO.setEmail(teacher.getEmail());
        userDTO.setLastname(teacher.getLastName());
        userDTO.setName(teacher.getName());
        userDTO.setPatronymic(teacher.getPatronymic());

        return userDTO;
    }

    public static SheduleDTO mapSheduleToSheduleDTO(Shedule shedule, Calendar calendar, Subject subject, Teacher teacher, Classroom classroom) {
        SheduleDTO sheduleDTO = new SheduleDTO();
        sheduleDTO.setDate(shedule.getDate());
        sheduleDTO.setHometask(shedule.getHometask());
        sheduleDTO.setClassroomName(classroom.getName());
        sheduleDTO.setLastnameTeacher(teacher.getLastName());
        sheduleDTO.setNameTeacher(teacher.getName());
        sheduleDTO.setPatronymicTeacher(teacher.getPatronymic());
        sheduleDTO.setLessonNumber(calendar.getLessonNumber());
        sheduleDTO.setSemestrId(calendar.getSemesterID());
        sheduleDTO.setSubjectName(subject.getSubjectName());

        return sheduleDTO;
    }

    public static Shedule mapSheduleDTOToShedule(SheduleDTO sheduleDTO, long calendarId, long teacherId, long subjectId, long classroomId) {
        Shedule shedule = new Shedule();
        shedule.setDate(sheduleDTO.getDate());
        shedule.setHometask(sheduleDTO.getHometask());
        shedule.setWeekDay(sheduleDTO.getWeekDay());
        shedule.setCalendarId(calendarId);
        shedule.setSubjectID(subjectId);
        shedule.setTeacherID(teacherId);
        shedule.setClassroomID(classroomId);

        return shedule;
    }

    public static Calendar mapSheduleDTOToCalendar(SheduleDTO sheduleDTO) {
        Calendar calendar = new Calendar();
        calendar.setLessonNumber(sheduleDTO.getLessonNumber());
        calendar.setWeekDay(sheduleDTO.getWeekDay());
        calendar.setSemesterID(sheduleDTO.getSemestrId());

        return calendar;
    }

    public static Classroom mapClassroomDTOToClassroom(ClassroomDTO classroomDTO, long teacherId) {
        Classroom classroom = new Classroom();
        classroom.setName(classroomDTO.getName());
        classroom.setClassroomTeacherId(teacherId);

        return classroom;
    }

    public static ClassroomDTO mapClassroomToClassroomDTO(Classroom classroom, Teacher teacher) {
        ClassroomDTO classroomDTO = new ClassroomDTO();
        classroomDTO.setName(classroom.getName());
        classroomDTO.setClassroomTeacherName(teacher.getName());
        classroomDTO.setClassroomTeacherLastname(teacher.getLastName());
        classroomDTO.setClassroomTeacherPatronymic(teacher.getPatronymic());

        return classroomDTO;
    }

    public static DiaryDTO mapToDiaryDTO(Shedule shedule, Pupil pupil, Classroom classroom, boolean attendance, String grade, Subject subject) {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setNamePupil(pupil.getName());
        diaryDTO.setLastnamePupil(pupil.getLastname());
        diaryDTO.setPatronymicPupil(pupil.getPatronymic());
        diaryDTO.setSubject(subject.getSubjectName());
        diaryDTO.setHomework(shedule.getHometask());
        diaryDTO.setGrade(grade);
        diaryDTO.setAttendance(attendance);
        diaryDTO.setDateLesson(shedule.getDate());
        diaryDTO.setClassName(classroom.getName());

        return diaryDTO;
    }*/
}
