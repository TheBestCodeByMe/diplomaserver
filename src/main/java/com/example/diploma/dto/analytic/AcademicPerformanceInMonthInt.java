package com.example.diploma.dto.analytic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public interface AcademicPerformanceInMonthInt {
    LocalDate getDate();
    int getGrade();
}