package com.example.diploma.mapper;

import com.example.diploma.dto.pupil.CreatePupilDTORequest;
import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.model.Classroom;
import com.example.diploma.model.Parents;
import com.example.diploma.model.Pupil;

import java.time.LocalDateTime;

import static java.sql.Timestamp.valueOf;

public class ParentsMapper {

    public static Parents mapPupilDTOToParents(CreatePupilDTORequest createPupilDTORequest, String parentsCode) {
        Parents parent = new Parents();
        parent.setNameMom(createPupilDTORequest.getNameMom());
        parent.setNameDad(createPupilDTORequest.getNameDad());
        parent.setLastnameDad(createPupilDTORequest.getLastnameDad());
        parent.setPatronymicDad(createPupilDTORequest.getPatronymicDad());
        parent.setLastnameMom(createPupilDTORequest.getLastnameMom());
        parent.setPatronymicMom(createPupilDTORequest.getPatronymicMom());
        parent.setCode(parentsCode);
        parent.setCreateDate(valueOf(LocalDateTime.now()));
        parent.setCloseDate(null);

        return parent;
    }
}
