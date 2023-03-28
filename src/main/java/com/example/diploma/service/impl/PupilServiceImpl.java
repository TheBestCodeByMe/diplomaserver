package com.example.diploma.service.impl;

import com.example.diploma.dao.ClassroomDao;
import com.example.diploma.dao.ParentsDao;
import com.example.diploma.dao.PupilDao;
import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.dto.pupil.PupilInClassDTO;
import com.example.diploma.mapper.PupilMapper;
import com.example.diploma.model.Classroom;
import com.example.diploma.model.Parents;
import com.example.diploma.model.Pupil;
import com.example.diploma.service.PupilService;
import com.example.diploma.repo.ClassroomRepository;
import com.example.diploma.repo.ParentsRepository;
import com.example.diploma.repo.PupilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PupilServiceImpl implements PupilService {

    private final PupilDao pupilDao;

    private final ParentsDao parentsDao;

    private final ClassroomDao classroomDao;

    @Override
    public PupilDTO getPupilByUserId(String userId) {
        Pupil pupil = pupilDao.findByUserId(Long.parseLong(userId));
        Parents parents;
        Classroom classroom;
        if(pupil!=null) {
            parents = parentsDao.findByParentsId(pupil.getParentsId());
            classroom = classroomDao.findByClassroomId(pupil.getClassroomId());
            return PupilMapper.mapToPupilDTO(pupil, parents, classroom);
        }
        return null;
    }

    @Override
    public List<PupilInClassDTO> getPupilsByClassName(String classname) {
        Classroom classroom = classroomDao.findClassroomByName(classname);
        List<Pupil> pupils = pupilDao.findAllByClassroomId(classroom.getId());
        List<PupilInClassDTO> pupilDtos = new ArrayList<>();
        pupils.forEach(pupil -> pupilDtos.add(PupilMapper.mapToPupilInClassDTO(pupil, classroom)));
        return pupilDtos;
    }
}