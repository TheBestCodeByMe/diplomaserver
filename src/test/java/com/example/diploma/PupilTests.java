package com.example.diploma;

import com.example.diploma.dto.teacher.CreateTeacherDTORequest;
import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.model.User;
import com.example.diploma.repo.PupilRepository;
import com.example.diploma.repo.UserRepository;
import com.example.diploma.service.impl.EditUsersServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class PupilTests {

    //@Autowired
    @Mock
    EditUsersServiceImpl editUsersService;

    @Mock
    PupilRepository pupilRepository;

    @Mock
    UserRepository userRepository;

    CreateTeacherDTORequest createTeacherDTORequest;

    @BeforeEach
    void init() {
        createTeacherDTORequest = new CreateTeacherDTORequest("name", "lastname", "patronymic", "email", "qualification", "position");
    }

    @Test
    void createTeacher() {
        ResponseEntity<TeacherDTO> teacherDTO = (ResponseEntity<TeacherDTO>) editUsersService.createTeacher(createTeacherDTORequest);
        Assertions.assertEquals("qualification", teacherDTO.getBody().getQualification());
    }

}
