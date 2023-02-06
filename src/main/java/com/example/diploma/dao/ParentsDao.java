package com.example.diploma.dao;

import com.example.diploma.model.Parents;
import com.example.diploma.model.Pupil;
import com.example.diploma.repo.ParentsRepository;
import com.example.diploma.repo.PupilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParentsDao {

    private final ParentsRepository parentsRepository;

    public Parents findByParentsId(Long parentsId) {
        return parentsRepository.getById(parentsId);
    }

    public Parents findByFIO(String nameDad, String lastnameDad, String patronymicDad, String nameMom, String lastnameMom, String patronymicMom) {
        return parentsRepository.findByNameDadAndLastnameDadAndPatronymicDadAndNameMomAndLastnameMomAndPatronymicMom(nameDad, lastnameDad, patronymicDad, nameMom, lastnameMom, patronymicMom);
    }

    public Parents findByCode(String code) {
        return parentsRepository.findByCode(code);
    }
}
