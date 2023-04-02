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
    private int grade;
    private boolean attendance;
    private LocalDate dateLesson;

    public DiaryBySubjectDTO(int grade, boolean attendance, LocalDate dateLesson) {
        this.grade = grade;
        this.attendance = attendance;
        this.dateLesson = dateLesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryBySubjectDTO that = (DiaryBySubjectDTO) o;
        return grade == that.grade && attendance == that.attendance && Objects.equals(dateLesson, that.dateLesson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grade, attendance, dateLesson);
    }
}
