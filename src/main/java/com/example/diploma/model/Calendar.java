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
@Table(name = "calendar")
@ToString
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="calendar_id")
    private Long id;
    @Column(name = "calendar_semester", nullable = false)
    private int semesterID;
    @Column(name = "calendar_week_day", nullable = false)
    private int weekDay;
    @Column(name = "lesson_calendar_id", nullable = false)
    private int lessonNumber;
    @Column(name = "calendar_status_id", nullable = false)
    private Long statusId;
    @Column(name = "calendar_code", nullable = false)
    private String code;
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Column(name = "close_date", nullable = true)
    private Date closeDate;


    public Calendar(int semesterID, int weekDay, int lessonNumber) {
        this.semesterID = semesterID;
        this.weekDay = weekDay;
        this.lessonNumber = lessonNumber;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return semesterID == calendar.semesterID && weekDay == calendar.weekDay && lessonNumber == calendar.lessonNumber && Objects.equals(id, calendar.id) && Objects.equals(statusId, calendar.statusId) && Objects.equals(code, calendar.code) && Objects.equals(createDate, calendar.createDate) && Objects.equals(closeDate, calendar.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, semesterID, weekDay, lessonNumber, statusId, code, createDate, closeDate);
    }
}