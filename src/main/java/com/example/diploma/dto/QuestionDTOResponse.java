package com.example.diploma.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionDTOResponse implements Serializable {
    private String question;
    private String response;
    private boolean flag;
    private String status;
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDTOResponse that = (QuestionDTOResponse) o;
        return flag == that.flag && Objects.equals(question, that.question) && Objects.equals(response, that.response) && Objects.equals(status, that.status) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, response, flag, status, code);
    }
}