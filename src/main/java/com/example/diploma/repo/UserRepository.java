package com.example.diploma.repo;

import com.example.diploma.model.Role;
import com.example.diploma.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByLogin(String login);
    Optional<User> findByLogin(String login);
    @Query("select u from User u where u.login = ?1")
    User findByLoginWithinOpt(String login);
    Boolean existsByLogin(String login);
    List<User> findAllByStatus(Long statusId);
    //@Query("select u.* from users u, role r, role_user ur where u.user_id = ur.user_id and r.role_id = ur.role_id and r.role_name = :role")
    //Optional<User> findByRole(String role);
}
