package com.example.diploma.repo;

import com.example.diploma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByLogin(String login);
    Optional<User> findByLogin(String login);
    Boolean existsByLogin(String login);
}
