package com.example.diploma.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "schedule")
@ToString
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="schedule_id")
    private Long id;
    @Column(name = "classroom_schedule_id", nullable = false)
    private Long classroomID; // TODO: сделать внешним ключом
    @Column(name = "subject_schedule_id", nullable = false)
    private Long subjectID; // TODO: сделать внешним ключом
    @Column(name = "teacher_schedule_id", nullable = false)
    private Long teacherID; // TODO: сделать внешним ключом
    @Column(name = "schedule_week_day", nullable = false)
    private int weekDay;
    @Column(name = "schedule_date", nullable = false)
    private LocalDate date;
    @Column(name = "calendar_schedule_id", nullable = false)
    private Long calendarId;
    @Column(name = "schedule_hometask")
    private String hometask;
    @Column(name = "schedule_status_id", nullable = false)
    private Long statusId;
    @Column(name = "schedule_code", nullable = false)
    private String code;
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate ;
    @Column(name = "close_date", nullable = true)
    private LocalDateTime closeDate ;

    public Schedule(Long classroomID, Long subjectID, Long teacherID, int weekDay, LocalDate date, Long calendarId, String hometask) {
        this.classroomID = classroomID;
        this.subjectID = subjectID;
        this.teacherID = teacherID;
        this.weekDay = weekDay;
        this.date = date;
        this.calendarId = calendarId;
        this.hometask = hometask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return weekDay == schedule.weekDay && Objects.equals(id, schedule.id) && Objects.equals(classroomID, schedule.classroomID) && Objects.equals(subjectID, schedule.subjectID) && Objects.equals(teacherID, schedule.teacherID) && Objects.equals(date, schedule.date) && Objects.equals(calendarId, schedule.calendarId) && Objects.equals(hometask, schedule.hometask) && Objects.equals(statusId, schedule.statusId) && Objects.equals(code, schedule.code) && Objects.equals(createDate, schedule.createDate) && Objects.equals(closeDate, schedule.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classroomID, subjectID, teacherID, weekDay, date, calendarId, hometask, statusId, code, createDate, closeDate);
    }
}
