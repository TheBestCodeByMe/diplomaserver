package com.example.diploma.dao;

import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Pupil;
import com.example.diploma.model.User;
import com.example.diploma.repo.PupilRepository;
import com.example.diploma.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;

    public User findByLogin(String login) {
        return userRepository.findByLoginWithinOpt(login);
    }

    public List<User> findAll() {return userRepository.findAllByStatus(EStatus.ACTIVE.getId());}
}
