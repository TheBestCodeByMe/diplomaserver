package com.example.diploma.dto.analytic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AcademicPerformanceInMonthDTO implements Serializable {
    private String month;
    private double averageGrade;

    public AcademicPerformanceInMonthDTO(String month, double averageGrade) {
        this.month = month;
        this.averageGrade = averageGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicPerformanceInMonthDTO that = (AcademicPerformanceInMonthDTO) o;
        return Double.compare(that.averageGrade, averageGrade) == 0 && Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, averageGrade);
    }
}