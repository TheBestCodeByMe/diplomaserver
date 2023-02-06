package com.example.diploma.dto.classroom;

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
public class ClassroomDTOSearch implements Serializable {
    private String name;
    private String classroomTeacherName;
    private String classroomTeacherLastname;
    private String classroomTeacherPatronymic;
    private String classroomCode;

    public ClassroomDTOSearch(String name, String classroomTeacherName, String classroomTeacherLastname, String classroomTeacherPatronymic, String classroomCode) {
        this.name = name;
        this.classroomTeacherName = classroomTeacherName;
        this.classroomTeacherLastname = classroomTeacherLastname;
        this.classroomTeacherPatronymic = classroomTeacherPatronymic;
        this.classroomCode = classroomCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomDTOSearch that = (ClassroomDTOSearch) o;
        return Objects.equals(name, that.name) && Objects.equals(classroomTeacherName, that.classroomTeacherName) && Objects.equals(classroomTeacherLastname, that.classroomTeacherLastname) && Objects.equals(classroomTeacherPatronymic, that.classroomTeacherPatronymic) && Objects.equals(classroomCode, that.classroomCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, classroomTeacherName, classroomTeacherLastname, classroomTeacherPatronymic, classroomCode);
    }
}