package com.example.diploma.dto.pupil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PupilInClassDTO implements Serializable {
    private String name;
    private String lastname;
    private String patronymic;
    private String className;
    private String pupilCode;

    public PupilInClassDTO(String name, String lastname, String patronymic, String pupilCode, String className) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.className = className;
        this.pupilCode = pupilCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PupilInClassDTO pupilDTO = (PupilInClassDTO) o;
        return Objects.equals(name, pupilDTO.name) && Objects.equals(lastname, pupilDTO.lastname) && Objects.equals(patronymic, pupilDTO.patronymic) && Objects.equals(className, pupilDTO.className) && Objects.equals(pupilCode, pupilDTO.pupilCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, patronymic, className, pupilCode);
    }
}