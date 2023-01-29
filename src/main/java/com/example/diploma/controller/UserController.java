package com.example.diploma.controller;

import com.example.diploma.dto.UserDTO;
import com.example.diploma.model.User;
import com.example.diploma.exception.ResourceNotFoundException;
import com.example.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        return userService.getUserById(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long userId,
                                           @Validated @RequestBody UserDTO userDetails) throws ResourceNotFoundException {
        return userService.updateUser(userId, userDetails);
    }
}
