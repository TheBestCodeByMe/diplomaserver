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
@Table(name = "classroom")
@ToString
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="classroom_id")
    private Long id;
    @Column(name = "teacher_classroom_id", nullable = false)
    private Long classroomTeacherId; // TODO: сделать внешним ключом
    @Column(name = "classroom_name", nullable = false)
    private String name;
    @Column(name = "classroom_status_id", nullable = false)
    private Long statusId;
    @Column(name = "classroom_code", nullable = false)
    private String code;
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Column(name = "close_date", nullable = true)
    private Date closeDate;


    public Classroom(Long classroomTeacherId, String name) {
        this.classroomTeacherId = classroomTeacherId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return Objects.equals(id, classroom.id) && Objects.equals(classroomTeacherId, classroom.classroomTeacherId) && Objects.equals(name, classroom.name) && Objects.equals(statusId, classroom.statusId) && Objects.equals(code, classroom.code) && Objects.equals(createDate, classroom.createDate) && Objects.equals(closeDate, classroom.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classroomTeacherId, name, statusId, code, createDate, closeDate);
    }
}