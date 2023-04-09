package com.example.diploma.service;

import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.dto.pupil.PupilInClassDTO;
import com.example.diploma.model.Pupil;

import java.util.List;

public interface PupilService {
   PupilDTO getPupilByUserId(String userId);
   List<PupilInClassDTO> getPupilDTOsByClassName(String classname);
   List<Pupil> getPupilsByClassname(String classname);
}
