package com.example.diploma.repo;

import com.example.diploma.model.Parents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentsRepository extends JpaRepository<Parents, Long> {
    Parents findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(String nameDad, String lastnameDad, String patronymicDad, String nameMom, String lastnameMom, String patronymicMom);
}
