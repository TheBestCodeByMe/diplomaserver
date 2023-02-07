package com.example.diploma.dto.pupil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PupilDTO implements Serializable {
    private String name;
    private String lastname;
    private String patronymic;
    private LocalDate dateOfBirthday;
    private String email;
    private String personalCheck;
    private String nameMom;
    private String lastnameMom;
    private String patronymicMom;
    private String nameDad;
    private String lastnameDad;
    private String patronymicDad;
    private String className;
    private String pupilCode;

    public PupilDTO(String name, String lastname, String patronymic, String pupilCode, LocalDate dateOfBirthday, String email, String personalCheck, String nameMom, String lastnameMom, String patronymicMom, String nameDad, String lastnameDad, String patronymicDad, String className) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.dateOfBirthday = dateOfBirthday;
        this.email = email;
        this.personalCheck = personalCheck;
        this.nameMom = nameMom;
        this.lastnameMom = lastnameMom;
        this.patronymicMom = patronymicMom;
        this.nameDad = nameDad;
        this.lastnameDad = lastnameDad;
        this.patronymicDad = patronymicDad;
        this.className = className;
        this.pupilCode = pupilCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PupilDTO pupilDTO = (PupilDTO) o;
        return Objects.equals(name, pupilDTO.name) && Objects.equals(lastname, pupilDTO.lastname) && Objects.equals(patronymic, pupilDTO.patronymic) && Objects.equals(dateOfBirthday, pupilDTO.dateOfBirthday) && Objects.equals(email, pupilDTO.email) && Objects.equals(personalCheck, pupilDTO.personalCheck) && Objects.equals(nameMom, pupilDTO.nameMom) && Objects.equals(lastnameMom, pupilDTO.lastnameMom) && Objects.equals(patronymicMom, pupilDTO.patronymicMom) && Objects.equals(nameDad, pupilDTO.nameDad) && Objects.equals(lastnameDad, pupilDTO.lastnameDad) && Objects.equals(patronymicDad, pupilDTO.patronymicDad) && Objects.equals(className, pupilDTO.className) && Objects.equals(pupilCode, pupilDTO.pupilCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, patronymic, dateOfBirthday, email, personalCheck, nameMom, lastnameMom, patronymicMom, nameDad, lastnameDad, patronymicDad, className, pupilCode);
    }
}