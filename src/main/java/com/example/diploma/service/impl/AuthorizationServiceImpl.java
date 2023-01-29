package com.example.diploma.service.impl;

import com.example.diploma.model.Pupil;
import com.example.diploma.model.Role;
import com.example.diploma.model.Teacher;
import com.example.diploma.model.User;
import com.example.diploma.enumiration.ERole;
import com.example.diploma.jwt.JwtUtils;
import com.example.diploma.pojo.JwtResponse;
import com.example.diploma.pojo.LoginRequest;
import com.example.diploma.pojo.MessageResponse;
import com.example.diploma.pojo.SignUpRequest;
import com.example.diploma.service.AuthorizationService;
import com.example.diploma.repo.PupilRepository;
import com.example.diploma.repo.RoleRepository;
import com.example.diploma.repo.TeacherRepository;
import com.example.diploma.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    // не работает
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PupilRepository pupilRepository;

    private final TeacherRepository teacherRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    @Override
    public ResponseEntity<?> authUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        // формируем jwt токен
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (userDetails.getId() != null) {
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    roles));
        } else {
            return ResponseEntity.badRequest().body("Your login or password are invalid");
        }
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpRequest signupRequest) {
        if (userRepository.existsByLogin(signupRequest.getLogin())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        Pupil pupil = pupilRepository.findByNameAndLastnameAndPatronymic(signupRequest.getName(), signupRequest.getLastname(), signupRequest.getPatronymic());
        Teacher teacher = teacherRepository.findByNameAndLastNameAndPatronymic(signupRequest.getName(), signupRequest.getLastname(), signupRequest.getPatronymic());

        if (signupRequest.getRole().equals("pupil")) {
            if (pupil == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Pupil is not exist"));
            }
        } else if (signupRequest.getRole().equals("teacher") || signupRequest.getRole().equals("Director")) {
            if (teacher == null) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Teacher is not exist"));
            }
        }

        User user = new User(signupRequest.getLogin(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getStatus());

        Set<String> reqRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Your role is null"));
/*            Role userRole = roleRepository
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
              roles.add(userRole);*/
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "pupil" -> {
                        Role adminRole = roleRepository
                                .findByName(ERole.ROLE_PUPIL)
                                .orElseThrow(() -> new RuntimeException("Error, Role Pupil is not found"));
                        roles.add(adminRole);
                    }
                    case "teacher" -> {
                        Role modRole = roleRepository
                                .findByName(ERole.ROLE_TEACHER)
                                .orElseThrow(() -> new RuntimeException("Error, Role Teacher is not found"));
                        roles.add(modRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        User userForId = userRepository.findByLogin(user.getLogin()).orElse(null);

        Set<Role> role = new HashSet<>();
        role.add(new Role(ERole.ROLE_PUPIL));

        if (userForId.getRoles().equals(role)) {
            pupil.setUserId(userForId.getId());
            pupil.setEmail(signupRequest.getEmail());
            pupilRepository.save(pupil);
        } else {
            teacher.setUserId(userForId.getId());
            teacher.setEmail(signupRequest.getEmail());
            teacherRepository.save(teacher);
        }
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}