package com.example.diploma.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pupil")
@ToString
public class Pupil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pupil_id")
    private Long id;
    @Column(name = "user_pupil_id")
    private long userId; // TODO: сделать внешним ключом
    @Column(name = "pupil_name", nullable = false)
    private String name;
    @Column(name = "pupil_lastname", nullable = false)
    private String lastname;
    @Column(name = "pupil_patronymic", nullable = false)
    private String patronymic;
    @Column(name = "pupil_date_of_birthday", nullable = false)
    private LocalDate dateOfBirthday;
    @Column(name = "pupil_email", nullable = false)
    private String email;
    @Column(name = "pupil_personal_check", nullable = false)
    private String personalCheck;
    @Column(name = "classroom_pupil_id", nullable = false)
    private long classroomId; // TODO: сделать внешним ключом
    @Column(name = "parents_pupil_id", nullable = false)
    private long parentsId; // TODO: сделать внешним ключом
    @Column(name = "pupil_status_id", nullable = false)
    private Long statusId;
    @Column(name = "pupil_code", nullable = false)
    private String code;
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate ;
    @Column(name = "close_date", nullable = true)
    private LocalDateTime closeDate ;


    public Pupil(long userId, String name, String lastname, String patronymic, LocalDate dateOfBirthday, String email, String personalCheck, long classroomId, long parentsId) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.dateOfBirthday = dateOfBirthday;
        this.email = email;
        this.personalCheck = personalCheck;
        this.classroomId = classroomId;
        this.parentsId = parentsId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pupil pupil = (Pupil) o;
        return userId == pupil.userId && classroomId == pupil.classroomId && parentsId == pupil.parentsId && Objects.equals(id, pupil.id) && Objects.equals(name, pupil.name) && Objects.equals(lastname, pupil.lastname) && Objects.equals(patronymic, pupil.patronymic) && Objects.equals(dateOfBirthday, pupil.dateOfBirthday) && Objects.equals(email, pupil.email) && Objects.equals(personalCheck, pupil.personalCheck) && Objects.equals(statusId, pupil.statusId) && Objects.equals(code, pupil.code) && Objects.equals(createDate, pupil.createDate) && Objects.equals(closeDate, pupil.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, lastname, patronymic, dateOfBirthday, email, personalCheck, classroomId, parentsId, statusId, code, createDate, closeDate);
    }
}