package com.example.diploma.dto.diary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiaryBySubjectDTO {
    private String grade;
    private boolean attendance;
    private LocalDate dateLesson;

    public DiaryBySubjectDTO(String grade, boolean attendance, LocalDate dateLesson) {
        this.grade = grade;
        this.attendance = attendance;
        this.dateLesson = dateLesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryBySubjectDTO createDiaryDTORequest = (DiaryBySubjectDTO) o;
        return attendance == createDiaryDTORequest.attendance && Objects.equals(grade, createDiaryDTORequest.grade) && Objects.equals(dateLesson, createDiaryDTORequest.dateLesson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, attendance, dateLesson);
    }
}
