package com.example.diploma.dto;

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
public class SheduleDTO implements Serializable {
    private Long id;
    private String classroomName;
    private String nameTeacher;
    private String lastnameTeacher;
    private String patronymicTeacher;
    private String subjectName;
    private int weekDay;
    private int lessonNumber;
    private Date date;
    private int semestrId;
    private String hometask;

    public SheduleDTO(String classroomName, String nameTeacher, String lastnameTeacher, String patronymicTeacher, String subjectName, int weekDay, int lessonNumber, Date date, int semestrId, String hometask) {
        this.classroomName = classroomName;
        this.nameTeacher = nameTeacher;
        this.lastnameTeacher = lastnameTeacher;
        this.patronymicTeacher = patronymicTeacher;
        this.subjectName = subjectName;
        this.weekDay = weekDay;
        this.lessonNumber = lessonNumber;
        this.date = date;
        this.semestrId = semestrId;
        this.hometask = hometask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SheduleDTO that = (SheduleDTO) o;
        return weekDay == that.weekDay && lessonNumber == that.lessonNumber && semestrId == that.semestrId && Objects.equals(id, that.id) && Objects.equals(classroomName, that.classroomName) && Objects.equals(nameTeacher, that.nameTeacher) && Objects.equals(lastnameTeacher, that.lastnameTeacher) && Objects.equals(patronymicTeacher, that.patronymicTeacher) && Objects.equals(subjectName, that.subjectName) && Objects.equals(date, that.date) && Objects.equals(hometask, that.hometask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classroomName, nameTeacher, lastnameTeacher, patronymicTeacher, subjectName, weekDay, lessonNumber, date, semestrId, hometask);
    }
}