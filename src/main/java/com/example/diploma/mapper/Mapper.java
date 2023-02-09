package com.example.diploma.mapper;

import com.example.diploma.dto.*;
import com.example.diploma.model.*;
import com.example.diploma.enumiration.ERole;

import java.util.HashSet;
import java.util.Set;

public class Mapper {
/*
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

    public static Calendar mapSheduleDTOToCalendar(SheduleDTO sheduleDTO) {
        Calendar calendar = new Calendar();
        calendar.setLessonNumber(sheduleDTO.getLessonNumber());
        calendar.setWeekDay(sheduleDTO.getWeekDay());
        calendar.setSemesterID(sheduleDTO.getSemestrId());

        return calendar;
    }
*/
}
