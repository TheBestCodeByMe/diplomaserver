package com.example.diploma.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "attendance")
@ToString
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="attendance_id")
    private Long id;
    @Column(name = "pupil_attendance_id", nullable = false)
    private Long pupilID; // TODO: сделать внешним ключом
    @Column(name = "class_attendance_id", nullable = false)
    private Long classID; // TODO: сделать внешним ключом
    @Column(name = "lesson_attendance_id", nullable = false)
    private Long lessonID; // TODO: сделать внешним ключом
    @Column(name = "attendance_status_id", nullable = false)
    private Long statusId;
    @Column(name = "attendance_code", nullable = false)
    private String code;
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Column(name = "close_date", nullable = true)
    private Date closeDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(id, that.id) && Objects.equals(pupilID, that.pupilID) && Objects.equals(classID, that.classID) && Objects.equals(lessonID, that.lessonID) && Objects.equals(statusId, that.statusId) && Objects.equals(code, that.code) && Objects.equals(createDate, that.createDate) && Objects.equals(closeDate, that.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pupilID, classID, lessonID, statusId, code, createDate, closeDate);
    }
}