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
@Table(name="subject")
@ToString
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_id")
    private Long id;
    @Column(name = "subject_name", nullable = false)
    private String subjectName;
    @Column(name = "subject_status_id", nullable = false)
    private Long statusId;
    @Column(name = "subject_code", nullable = false)
    private String code;
    @Column(name = "create_date", nullable = false)
    private Date createDate;
    @Column(name = "close_date", nullable = true)
    private Date closeDate;

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public Subject(String subjectName, Long statusId, String code, Date createDate, Date closeDate) {
        this.subjectName = subjectName;
        this.statusId = statusId;
        this.code = code;
        this.createDate = createDate;
        this.closeDate = closeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(subjectName, subject.subjectName) && Objects.equals(statusId, subject.statusId) && Objects.equals(code, subject.code) && Objects.equals(createDate, subject.createDate) && Objects.equals(closeDate, subject.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectName, statusId, code, createDate, closeDate);
    }
}
