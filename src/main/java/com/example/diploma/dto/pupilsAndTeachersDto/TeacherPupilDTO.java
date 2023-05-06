package com.example.diploma.dto.pupilsAndTeachersDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TeacherPupilDTO implements Serializable {
    private String name;
    private String lastName;
    private String patronymic;
    private String email;
    private String code;
    private Boolean isPupil;

    public TeacherPupilDTO(String name, String lastName, String patronymic, String email, String code, Boolean isPupil) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.code = code;
        this.isPupil = isPupil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherPupilDTO that = (TeacherPupilDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(patronymic, that.patronymic) && Objects.equals(email, that.email) && Objects.equals(code, that.code) && Objects.equals(isPupil, that.isPupil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, patronymic, email, code, isPupil);
    }
}