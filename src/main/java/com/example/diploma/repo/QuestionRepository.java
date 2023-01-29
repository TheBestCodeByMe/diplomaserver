package com.example.diploma.repo;

import com.example.diploma.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByQuestion(String question);
}
