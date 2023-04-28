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
public class AttendanceInMonthDTO implements Serializable {
    private String month;
    private int count;

    public AttendanceInMonthDTO(String month, int count) {
        this.month = month;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendanceInMonthDTO that = (AttendanceInMonthDTO) o;
        return count == that.count && Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, count);
    }
}