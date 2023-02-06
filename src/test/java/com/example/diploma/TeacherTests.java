package com.example.diploma;

import com.example.diploma.dto.teacher.CreateTeacherDTORequest;
import com.example.diploma.dto.teacher.TeacherDTO;
import com.example.diploma.service.EditUsersService;
import com.example.diploma.service.impl.EditUsersServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class TeacherTests {

    @Autowired
    EditUsersServiceImpl editUsersService;

    @Test
    void createTeacher() {
        CreateTeacherDTORequest createTeacherDTORequest = new CreateTeacherDTORequest("name", "lastname", "patronymic", "email", "qualification", "position");
        ResponseEntity<TeacherDTO> teacherDTO = (ResponseEntity<TeacherDTO>) editUsersService.createTeacher(createTeacherDTORequest);
        Assertions.assertEquals("qualification", teacherDTO.getBody().getQualification());
    }

}
