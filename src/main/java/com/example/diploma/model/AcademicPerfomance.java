package com.example.diploma.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "academic_performance")
@ToString
public class AcademicPerfomance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="academic_performance_id")
    private Long id;
    @Column(name = "pupil_academic_performance_id", nullable = false)
    private Long pupilID; // TODO: сделать внешним ключом
    @Column(name = "class_academic_performance_id", nullable = false)
    private Long classID; // TODO: сделать внешним ключом
    @Column(name = "lesson_academic_performance_id", nullable = false)
    private Long lessonID; // TODO: сделать внешним ключом
    @Column(name = "academic_performance_grade", nullable = false)
    private int grade;
    @Column(name = "academic_performance_status_id", nullable = false)
    private Long statusId;
    @Column(name = "academic_performance_code", nullable = false)
    private String code;
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate ;
    @Column(name = "close_date", nullable = true)
    private LocalDateTime closeDate ;

    public AcademicPerfomance(Long pupilID, Long classID, Long lessonID, int grade) {
        this.pupilID = pupilID;
        this.classID = classID;
        this.lessonID = lessonID;
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicPerfomance that = (AcademicPerfomance) o;
        return grade == that.grade && Objects.equals(id, that.id) && Objects.equals(pupilID, that.pupilID) && Objects.equals(classID, that.classID) && Objects.equals(lessonID, that.lessonID) && Objects.equals(statusId, that.statusId) && Objects.equals(code, that.code) && Objects.equals(createDate, that.createDate) && Objects.equals(closeDate, that.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pupilID, classID, lessonID, grade, statusId, code, createDate, closeDate);
    }
}
