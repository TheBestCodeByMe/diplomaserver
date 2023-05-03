package com.example.diploma.dto.analytic;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademicPerformanceInMonthDTODB {
    private LocalDate date;
    private int grade;
}