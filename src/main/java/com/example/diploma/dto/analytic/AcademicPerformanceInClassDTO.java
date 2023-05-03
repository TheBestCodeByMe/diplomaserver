package com.example.diploma.dto.analytic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AcademicPerformanceInClassDTO implements Serializable {
    private String performance;
    private int count;

    public AcademicPerformanceInClassDTO(String performance, int count) {
        this.performance = performance;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicPerformanceInClassDTO that = (AcademicPerformanceInClassDTO) o;
        return count == that.count && Objects.equals(performance, that.performance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(performance, count);
    }
}