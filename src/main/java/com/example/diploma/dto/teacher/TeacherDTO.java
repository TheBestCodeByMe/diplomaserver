package com.example.diploma.dto.teacher;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TeacherDTO implements Serializable {
    private String name;
    private String lastName;
    private String patronymic;
    private String email;
    private String qualification;
    private String position;
    private String statusName;
    private String code;

    public TeacherDTO(String name, String lastName, String patronymic, String email, String qualification, String position, String statusName, String code) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.qualification = qualification;
        this.position = position;
        this.statusName = statusName;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDTO that = (TeacherDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(patronymic, that.patronymic) && Objects.equals(email, that.email) && Objects.equals(qualification, that.qualification) && Objects.equals(position, that.position) && Objects.equals(statusName, that.statusName) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, patronymic, email, qualification, position, statusName, code);
    }
}