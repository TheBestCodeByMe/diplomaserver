package com.example.diploma.dto.schedule;

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
public class CreateScheduleDTORequest implements Serializable {
    private String classroomName;
    private String nameTeacher;
    private String lastnameTeacher;
    private String patronymicTeacher;
    private String subjectName;
    private int weekDay;
    private int lessonNumber;
    private LocalDate date;
    private int semesterId;
    private String hometask;

    public CreateScheduleDTORequest(String classroomName, String nameTeacher, String lastnameTeacher, String patronymicTeacher, String subjectName, int weekDay, int lessonNumber, LocalDate date, int semesterId, String hometask) {
        this.classroomName = classroomName;
        this.nameTeacher = nameTeacher;
        this.lastnameTeacher = lastnameTeacher;
        this.patronymicTeacher = patronymicTeacher;
        this.subjectName = subjectName;
        this.weekDay = weekDay;
        this.lessonNumber = lessonNumber;
        this.date = date;
        this.semesterId = semesterId;
        this.hometask = hometask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateScheduleDTORequest that = (CreateScheduleDTORequest) o;
        return weekDay == that.weekDay && lessonNumber == that.lessonNumber && semesterId == that.semesterId && Objects.equals(classroomName, that.classroomName) && Objects.equals(nameTeacher, that.nameTeacher) && Objects.equals(lastnameTeacher, that.lastnameTeacher) && Objects.equals(patronymicTeacher, that.patronymicTeacher) && Objects.equals(subjectName, that.subjectName) && Objects.equals(date, that.date) && Objects.equals(hometask, that.hometask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroomName, nameTeacher, lastnameTeacher, patronymicTeacher, subjectName, weekDay, lessonNumber, date, semesterId, hometask);
    }
}