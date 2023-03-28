package com.example.diploma.mapper;

import com.example.diploma.dto.pupil.CreatePupilDTORequest;
import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.dto.pupil.PupilInClassDTO;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Classroom;
import com.example.diploma.model.Parents;
import com.example.diploma.model.Pupil;

import java.time.LocalDateTime;

import static java.sql.Timestamp.valueOf;

public class PupilMapper {

    public static PupilDTO mapToPupilDTO(Pupil pupils, Parents parents, Classroom classrooms) {
        PupilDTO pupilDTOS = new PupilDTO();
        pupilDTOS.setName(pupils.getName());
        pupilDTOS.setLastname(pupils.getLastname());
        pupilDTOS.setPatronymic(pupils.getPatronymic());
        pupilDTOS.setEmail(pupils.getEmail());
        pupilDTOS.setDateOfBirthday(pupils.getDateOfBirthday());
        pupilDTOS.setPersonalCheck(pupils.getPersonalCheck());
        pupilDTOS.setClassName(classrooms.getName());
        pupilDTOS.setNameMom(parents.getNameMom());
        pupilDTOS.setNameDad(parents.getNameDad());
        pupilDTOS.setLastnameDad(parents.getLastnameDad());
        pupilDTOS.setPatronymicDad(parents.getPatronymicDad());
        pupilDTOS.setLastnameMom(parents.getLastnameMom());
        pupilDTOS.setPatronymicMom(parents.getPatronymicMom());
        pupilDTOS.setPupilCode(pupils.getCode());

        return pupilDTOS;
    }

    public static Pupil mapPupilDTOToPupil(CreatePupilDTORequest pupilDTOs, String pupilCode) {
        Pupil pupil = new Pupil();

        pupil.setName(pupilDTOs.getName());
        pupil.setLastname(pupilDTOs.getLastname());
        pupil.setPatronymic(pupilDTOs.getPatronymic());
        pupil.setEmail(pupilDTOs.getEmail());
        pupil.setDateOfBirthday(pupilDTOs.getDateOfBirthday());
        pupil.setPersonalCheck(pupilDTOs.getPersonalCheck());
        pupil.setStatusId(EStatus.ACTIVE.getId());
        pupil.setCode(pupilCode);
        pupil.setCreateDate(LocalDateTime.now());
        pupil.setCloseDate(null);
        pupil.setUserId(2);

        return pupil;
    }

    public static PupilInClassDTO mapToPupilInClassDTO(Pupil pupils, Classroom classrooms) {
        PupilInClassDTO pupilDTOS = new PupilInClassDTO();
        pupilDTOS.setName(pupils.getName());
        pupilDTOS.setLastname(pupils.getLastname());
        pupilDTOS.setPatronymic(pupils.getPatronymic());
        pupilDTOS.setClassName(classrooms.getName());
        pupilDTOS.setPupilCode(pupils.getCode());

        return pupilDTOS;
    }
}
