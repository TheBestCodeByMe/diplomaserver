package com.example.diploma.service.impl;

import com.example.diploma.enumiration.ERole;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Role;
import com.example.diploma.model.User;
import com.example.diploma.repo.RoleRepository;
import com.example.diploma.repo.UserRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

/*
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByLogin(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found with  username: " + username));
        return UserDetailsImpl.build(user);
    }

/*    @PostConstruct
    public void init() {
        Set<Role> role = new HashSet<>();
        role.add(new Role(ERole.ROLE_DIRECTOR));

        Arrays.stream(ERole.values()).forEach(rol ->
        roleRepository.findByName(rol).orElse(
                roleRepository.save(new Role(rol))
        ));

        User user = userRepository
                .findByRole(ERole.ROLE_DIRECTOR.name())
                .orElse(userRepository.save(
                        new User(
                                "root",
                                passwordEncoder.encode("root"),
                                role,
                                EStatus.ACTIVE.getId()
                        )
                ));
        System.out.printf("_____? %s Bean ______ init() ______\n", getClass().getSimpleName());
    }*/
}
