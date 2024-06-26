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
@Table(name = "questions_from_users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "questions_from_users_id")
    private Long id;
    @Column(name = "questions_from_users_question", nullable = false)
    private String question;
    @Column(name = "questions_from_users_response")
    private String response;
    @Column(name = "questions_from_users_flag")
    private boolean flag;
    @Column(name = "questions_from_users_status_id", nullable = false)
    private Long statusId;
    @Column(name = "questions_from_users_code", nullable = false)
    private String code;
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate ;
    @Column(name = "close_date", nullable = true)
    private LocalDateTime closeDate ;

    public Question(String question, boolean flag, Long statusId, String code, LocalDateTime createDate ) {
        this.question = question;
        this.flag = flag;
        this.statusId = statusId;
        this.code = code;
        this.createDate = createDate;
    }

    public Question(String question, String response, boolean flag, Long statusId, String code, LocalDateTime createDate , LocalDateTime closeDate ) {
        this.question = question;
        this.response = response;
        this.flag = flag;
        this.statusId = statusId;
        this.code = code;
        this.createDate = createDate;
        this.closeDate = closeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return flag == question1.flag && Objects.equals(id, question1.id) && Objects.equals(question, question1.question) && Objects.equals(response, question1.response) && Objects.equals(statusId, question1.statusId) && Objects.equals(code, question1.code) && Objects.equals(createDate, question1.createDate) && Objects.equals(closeDate, question1.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, response, flag, statusId, code, createDate, closeDate);
    }
}
