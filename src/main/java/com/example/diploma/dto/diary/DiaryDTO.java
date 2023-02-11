package com.example.diploma.dto.diary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiaryDTO {
    private String namePupil;
    private String lastnamePupil;
    private String patronymicPupil;
    private String subject;
    private String homework;
    private String grade;
    private boolean attendance;
    private LocalDate dateLesson;
    private String className;

    public DiaryDTO(String namePupil, String lastnamePupil, String patronymicPupil, String subject, String homework, String grade, boolean attendance, LocalDate dateLesson, String className) {
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
        DiaryDTO createDiaryDTORequest = (DiaryDTO) o;
        return attendance == createDiaryDTORequest.attendance && Objects.equals(namePupil, createDiaryDTORequest.namePupil) && Objects.equals(lastnamePupil, createDiaryDTORequest.lastnamePupil) && Objects.equals(patronymicPupil, createDiaryDTORequest.patronymicPupil) && Objects.equals(subject, createDiaryDTORequest.subject) && Objects.equals(homework, createDiaryDTORequest.homework) && Objects.equals(grade, createDiaryDTORequest.grade) && Objects.equals(dateLesson, createDiaryDTORequest.dateLesson) && Objects.equals(className, createDiaryDTORequest.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namePupil, lastnamePupil, patronymicPupil, subject, homework, grade, attendance, dateLesson, className);
    }
}
