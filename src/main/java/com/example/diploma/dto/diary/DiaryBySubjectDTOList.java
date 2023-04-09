package com.example.diploma.dto.diary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiaryBySubjectDTOList {
    private String namePupil;
    private String lastnamePupil;
    private String patronymicPupil;
    private String pupilCode;
    private Collection<DiaryBySubjectDTO> diary;
    private double averageScore;
    private int countAttendances;
    private int semesterGrade;

    public DiaryBySubjectDTOList(String namePupil, String lastnamePupil, String patronymicPupil, String pupilCode, Collection<DiaryBySubjectDTO> diary, double averageScore, int countAttendances, int semesterGrade) {
        this.namePupil = namePupil;
        this.lastnamePupil = lastnamePupil;
        this.patronymicPupil = patronymicPupil;
        this.pupilCode = pupilCode;
        this.diary = diary;
        this.averageScore = averageScore;
        this.countAttendances = countAttendances;
        this.semesterGrade = semesterGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryBySubjectDTOList that = (DiaryBySubjectDTOList) o;
        return Double.compare(that.averageScore, averageScore) == 0 && countAttendances == that.countAttendances && semesterGrade == that.semesterGrade && Objects.equals(namePupil, that.namePupil) && Objects.equals(lastnamePupil, that.lastnamePupil) && Objects.equals(patronymicPupil, that.patronymicPupil) && Objects.equals(pupilCode, that.pupilCode) && Objects.equals(diary, that.diary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namePupil, lastnamePupil, patronymicPupil, pupilCode, diary, averageScore, countAttendances, semesterGrade);
    }
}
