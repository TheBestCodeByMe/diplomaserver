package com.example.diploma.dto.pupil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreatePupilDTORequest implements Serializable {
    private String name;
    private String lastname;
    private String patronymic;
    private Date dateOfBirthday;
    private String email;
    private String personalCheck;
    private String nameMom;
    private String lastnameMom;
    private String patronymicMom;
    private String nameDad;
    private String lastnameDad;
    private String patronymicDad;
    private String className;

    public CreatePupilDTORequest(String name, String lastname, String patronymic, Date dateOfBirthday, String email, String personalCheck, String nameMom, String lastnameMom, String patronymicMom, String nameDad, String lastnameDad, String patronymicDad, String className) {
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePupilDTORequest that = (CreatePupilDTORequest) o;
        return Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname) && Objects.equals(patronymic, that.patronymic) && Objects.equals(dateOfBirthday, that.dateOfBirthday) && Objects.equals(email, that.email) && Objects.equals(personalCheck, that.personalCheck) && Objects.equals(nameMom, that.nameMom) && Objects.equals(lastnameMom, that.lastnameMom) && Objects.equals(patronymicMom, that.patronymicMom) && Objects.equals(nameDad, that.nameDad) && Objects.equals(lastnameDad, that.lastnameDad) && Objects.equals(patronymicDad, that.patronymicDad) && Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastname, patronymic, dateOfBirthday, email, personalCheck, nameMom, lastnameMom, patronymicMom, nameDad, lastnameDad, patronymicDad, className);
    }
}