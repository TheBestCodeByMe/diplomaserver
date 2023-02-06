package com.example.diploma.mapper;

import com.example.diploma.dto.subject.CreateSubjectDTORequest;
import com.example.diploma.dto.subject.SubjectDTO;
import com.example.diploma.enumiration.EStatus;
import com.example.diploma.model.Subject;
import java.time.LocalDateTime;
import java.util.Locale;

import static java.sql.Timestamp.valueOf;

public class SubjectMapper {

    public static Subject mapSubjectDTOToSubject(CreateSubjectDTORequest createSubjectDTORequest, String subjectCode) {
        return new Subject(createSubjectDTORequest.getName().toLowerCase(Locale.ROOT), EStatus.ACTIVE.getId(), subjectCode, valueOf(LocalDateTime.now()), null);
    }

    public static SubjectDTO mapSubjectToSubjectDTO(Subject subject) {
        return new SubjectDTO(subject.getSubjectName(), EStatus.getName(subject.getStatusId()), subject.getCode());
    }
}
