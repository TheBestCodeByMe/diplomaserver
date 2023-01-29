package com.example.diploma.dto;

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
public class ClassroomDTO implements Serializable {
    private Long id;
    private String name;
    private String classroomTeacherName;
    private String classroomTeacherLastname;
    private String classroomTeacherPatronymic;

    public ClassroomDTO(String name, String classroomTeacherName, String classroomTeacherLastname, String classroomTeacherPatronymic) {
        this.name = name;
        this.classroomTeacherName = classroomTeacherName;
        this.classroomTeacherLastname = classroomTeacherLastname;
        this.classroomTeacherPatronymic = classroomTeacherPatronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomDTO that = (ClassroomDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(classroomTeacherName, that.classroomTeacherName) && Objects.equals(classroomTeacherLastname, that.classroomTeacherLastname) && Objects.equals(classroomTeacherPatronymic, that.classroomTeacherPatronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, classroomTeacherName, classroomTeacherLastname, classroomTeacherPatronymic);
    }
}