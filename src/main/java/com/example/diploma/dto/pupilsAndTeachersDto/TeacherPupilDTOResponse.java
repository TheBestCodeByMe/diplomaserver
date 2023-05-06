package com.example.diploma.dto.pupilsAndTeachersDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TeacherPupilDTOResponse implements Serializable {
    private List<TeacherPupilDTO> teacherPupilDTOList;

    public TeacherPupilDTOResponse(List<TeacherPupilDTO> teacherPupilDTOList) {
        this.teacherPupilDTOList = teacherPupilDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherPupilDTOResponse that = (TeacherPupilDTOResponse) o;
        return Objects.equals(teacherPupilDTOList, that.teacherPupilDTOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherPupilDTOList);
    }
}