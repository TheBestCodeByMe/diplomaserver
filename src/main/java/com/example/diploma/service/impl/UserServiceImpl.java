package com.example.diploma.service.impl;

import com.example.diploma.dto.UserDTO;
import com.example.diploma.model.Pupil;
import com.example.diploma.model.Teacher;
import com.example.diploma.model.User;
import com.example.diploma.exception.ResourceNotFoundException;
import com.example.diploma.mapper.Mapper;
import com.example.diploma.service.UserService;
import com.example.diploma.repo.PupilRepository;
import com.example.diploma.repo.TeacherRepository;
import com.example.diploma.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PupilRepository pupilRepository;

    private final TeacherRepository teacherRepository;/*

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }*/
/*
    @Override
    public ResponseEntity<UserDTO> getUserById(Long userId)
            throws ResourceNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId)));
        Pupil pupil = pupilRepository.findByUserId(userId);
        Teacher teacher = teacherRepository.findByUserId(userId);
        UserDTO userDTO;
        if(pupil != null){
            userDTO = Mapper.mapUserToUserDTO(user.get(), pupil);
        } else {
            userDTO = Mapper.mapUserTeacherToUserDTO(user.get(), teacher);
        }

        return ResponseEntity.ok().body(userDTO);
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(Long userId,
                                              UserDTO userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        Pupil pupil = pupilRepository.findByUserId(userId);
        Teacher teacher = teacherRepository.findByUserId(userId);

        if(pupil != null){
            pupil.setEmail(userDetails.getEmail());
            pupil.setName(userDetails.getName());
            pupil.setLastname(userDetails.getLastname());
            pupil.setPatronymic(userDetails.getPatronymic());
            pupilRepository.save(pupil);
        } else {
            teacher.setEmail(userDetails.getEmail());
            teacher.setName(userDetails.getName());
            teacher.setLastName(userDetails.getLastname());
            teacher.setPatronymic(userDetails.getPatronymic());
            teacherRepository.save(teacher);
        }

        user.setLogin(userDetails.getLogin());

        userRepository.save(user);
        return ResponseEntity.ok(userDetails);
    }*/
}