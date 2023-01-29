package com.example.diploma.service.impl;

import com.example.diploma.dto.PupilDTO;
import com.example.diploma.model.Classroom;
import com.example.diploma.model.Parents;
import com.example.diploma.model.Pupil;
import com.example.diploma.map.Mapper;
import com.example.diploma.service.PupilService;
import com.example.diploma.repo.ClassroomRepository;
import com.example.diploma.repo.ParentsRepository;
import com.example.diploma.repo.PupilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PupilServiceImpl implements PupilService {

    private final PupilRepository pupilRepository;

    private final ParentsRepository parentsRepository;

    private final ClassroomRepository classroomRepository;

    @Override
    public PupilDTO getPupilByFIO(String userId) {
        Pupil pupil = pupilRepository.findByUserId(Long.parseLong(userId));
        Parents parents = parentsRepository.getById(pupil.getParentsId());
        Classroom classroom = classroomRepository.getById(pupil.getClassroomId());
        return Mapper.mapToPupilDTO(pupil, parents, classroom);
    }
}