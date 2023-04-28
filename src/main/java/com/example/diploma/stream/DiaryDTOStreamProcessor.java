package com.example.diploma.stream;

import com.example.diploma.model.*;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class DiaryDTOStreamProcessor {
    private Pupil pupil;
    private Subject subject;
    private Schedule schedule;
    private Classroom classroom;
    private boolean isAcademicPerform;
    private AcademicPerfomance academicPerformance;
    private boolean attend;
    private Attendance attendance;
    private ResponseEntity<?> responseEntity;
}
