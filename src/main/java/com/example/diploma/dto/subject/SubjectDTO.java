package com.example.diploma.dto.subject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubjectDTO implements Serializable {
    private String name;
    private String statusName;
    private String code;

    public SubjectDTO(String name, String statusName, String code) {
        this.name = name;
        this.statusName = statusName;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDTO that = (SubjectDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(statusName, that.statusName) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, statusName, code);
    }
}