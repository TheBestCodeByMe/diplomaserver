package com.example.diploma.service;

import com.example.diploma.dto.AllUserDTO;
import com.example.diploma.dto.UserDTO;
import com.example.diploma.model.User;
import com.example.diploma.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<AllUserDTO> getAllActiveUsers();

    List<AllUserDTO> getAllUsers();

    ResponseEntity<UserDTO> getUserById(Long userId)
            throws ResourceNotFoundException;

    ResponseEntity<UserDTO> updateUser(Long userId,
                                       UserDTO userDetails) throws ResourceNotFoundException;
}
