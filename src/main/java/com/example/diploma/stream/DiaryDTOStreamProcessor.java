package com.example.diploma.stream;

import com.example.diploma.model.Classroom;
import com.example.diploma.model.Pupil;
import com.example.diploma.model.Schedule;
import com.example.diploma.model.Subject;
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
    private boolean academicPerformance;
    private boolean attendance;
    private ResponseEntity<?> responseEntity;
}
