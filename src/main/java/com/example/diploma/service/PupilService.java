package com.example.diploma.service;

import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.dto.pupil.PupilInClassDTO;

import java.util.List;

public interface PupilService {
   PupilDTO getPupilByUserId(String userId);

   List<PupilInClassDTO> getPupilsByClassName(String classname);
}
