package com.example.diploma.dto.diary;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiaryBySubjectDTOResponse {
    private String subject;
    private String classname;
    private List<DiaryBySubjectDTOList> diaries;

    public DiaryBySubjectDTOResponse(String subject, String classname, List<DiaryBySubjectDTOList> diaries) {
        this.subject = subject;
        this.classname = classname;
        this.diaries = diaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiaryBySubjectDTOResponse that = (DiaryBySubjectDTOResponse) o;
        return Objects.equals(subject, that.subject) && Objects.equals(classname, that.classname) && Objects.equals(diaries, that.diaries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, classname, diaries);
    }
}
