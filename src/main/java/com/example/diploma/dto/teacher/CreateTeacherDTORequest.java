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
public class CreateTeacherDTORequest implements Serializable {
    private String name;
    private String lastName;
    private String patronymic;
    private String email;
    private String qualification;
    private String position;

    public CreateTeacherDTORequest(String name, String lastName, String patronymic, String email, String qualification, String position) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.qualification = qualification;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTeacherDTORequest that = (CreateTeacherDTORequest) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(patronymic, that.patronymic) && Objects.equals(email, that.email) && Objects.equals(qualification, that.qualification) && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, patronymic, email, qualification, position);
    }
}