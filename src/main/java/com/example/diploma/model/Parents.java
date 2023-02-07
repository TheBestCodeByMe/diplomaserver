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
@Getter
@Setter
@NoArgsConstructor
@Table(name = "parents")
@ToString
public class Parents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="parents_id")
    private Long id;
    @Column(name = "parents_name_mom", nullable = false)
    private String nameMom;
    @Column(name = "parents_lastname_mom", nullable = false)
    private String lastnameMom;
    @Column(name = "parents_patronymic_mom", nullable = false)
    private String patronymicMom;
    @Column(name = "parents_name_dad", nullable = false)
    private String nameDad;
    @Column(name = "parents_lastname_dad", nullable = false)
    private String lastnameDad;
    @Column(name = "parents_patronymic_dad", nullable = false)
    private String patronymicDad;
    @Column(name = "parents_status_id", nullable = false)
    private Long statusId;
    @Column(name = "parents_code", nullable = false)
    private String code;
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate ;
    @Column(name = "close_date", nullable = true)
    private LocalDateTime closeDate ;


    public Parents(String nameMom, String lastnameMom, String patronymicMom, String nameDad, String lastnameDad, String patronymicDad) {
        this.nameMom = nameMom;
        this.lastnameMom = lastnameMom;
        this.patronymicMom = patronymicMom;
        this.nameDad = nameDad;
        this.lastnameDad = lastnameDad;
        this.patronymicDad = patronymicDad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parents parents = (Parents) o;
        return Objects.equals(id, parents.id) && Objects.equals(nameMom, parents.nameMom) && Objects.equals(lastnameMom, parents.lastnameMom) && Objects.equals(patronymicMom, parents.patronymicMom) && Objects.equals(nameDad, parents.nameDad) && Objects.equals(lastnameDad, parents.lastnameDad) && Objects.equals(patronymicDad, parents.patronymicDad) && Objects.equals(statusId, parents.statusId) && Objects.equals(code, parents.code) && Objects.equals(createDate, parents.createDate) && Objects.equals(closeDate, parents.closeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameMom, lastnameMom, patronymicMom, nameDad, lastnameDad, patronymicDad, statusId, code, createDate, closeDate);
    }
}
