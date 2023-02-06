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
@Table(name="teacher")
@ToString
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="teacher_id")
    private Long id;
    @Column(name = "user_teacher_id", nullable = true)
    private long userId; // TODO: сделать внешним ключом
    @Column(name = "teacher_name", nullable = false)
    private String name;
    @Column(name = "teacher_lastname", nullable = false)
    private String lastName;
    @Column(name = "teacher_patronymic", nullable = false)
    private String patronymic;
    @Column(name = "teacher_email")
    private String email;
    @Column(name = "teacher_qualification", nullable = false)
    private String qualification;
    @Column(name = "teacher_position", nullable = false)
    private String position;
    @Column(name = "teacher_status_id", nullable = false)
    private Long statusId;
    @Column(name = "teacher_code", nullable = false)
    private String code;
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Column(name = "close_date", nullable = true)
    private Date closeDate;

    public Teacher(Long userId, String name, String lastName, String patronymic, String email, String qualification, String position) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.qualification = qualification;
        this.position = position;
    }

    public Teacher(long userId, String name, String lastName, String patronymic, String email, String qualification, String position, Long statusId, String code, Date createDate, Date closeDate) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.qualification = qualification;
        this.position = position;
        this.statusId = statusId;
        this.code = code;
        this.createDate = createDate;
        this.closeDate = closeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return userId == teacher.userId && Objects.equals(id, teacher.id) && Objects.equals(name, teacher.name) && Objects.equals(lastName, teacher.lastName) && Objects.equals(patronymic, teacher.patronymic) && Objects.equals(email, teacher.email) && Objects.equals(qualification, teacher.qualification) && Objects.equals(position, teacher.position) && Objects.equals(statusId, teacher.statusId) && Objects.equals(code, teacher.code) && Objects.equals(createDate, teacher.createDate) && Objects.equals(closeDate, teacher.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, lastName, patronymic, email, qualification, position, statusId, code, createDate, closeDate);
    }
}
