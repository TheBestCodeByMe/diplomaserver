package com.example.diploma.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiaryDTO {
    private Long id;
    private String namePupil;
    private String lastnamePupil;
    private String patronymicPupil;
    private String subject;
    private String homework;
    private String grade;
    private boolean attendance;
    private Date dateLesson;
    private String className;

    public DiaryDTO(String namePupil, String lastnamePupil, String patronymicPupil, String subject, String homework, String grade, boolean attendance, Date dateLesson, String className) {
        this.namePupil = namePupil;
        this.lastnamePupil = lastnamePupil;
        this.patronymicPupil = patronymicPupil;
        this.subject = subject;
        this.homework = homework;
        this.grade = grade;
        this.attendance = attendance;
        this.dateLesson = dateLesson;
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryDTO diaryDTO = (DiaryDTO) o;
        return attendance == diaryDTO.attendance && Objects.equals(id, diaryDTO.id) && Objects.equals(namePupil, diaryDTO.namePupil) && Objects.equals(lastnamePupil, diaryDTO.lastnamePupil) && Objects.equals(patronymicPupil, diaryDTO.patronymicPupil) && Objects.equals(subject, diaryDTO.subject) && Objects.equals(homework, diaryDTO.homework) && Objects.equals(grade, diaryDTO.grade) && Objects.equals(dateLesson, diaryDTO.dateLesson) && Objects.equals(className, diaryDTO.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namePupil, lastnamePupil, patronymicPupil, subject, homework, grade, attendance, dateLesson, className);
    }
}
