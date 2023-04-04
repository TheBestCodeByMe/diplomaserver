package com.example.diploma.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ScheduleDatesDTO implements Serializable {
    private LocalDate dateSchedule;
    private int semester;
    private String hometask;
    private String scheduleCode;

    public ScheduleDatesDTO(LocalDate dateSchedule, int semester, String hometask, String scheduleCode) {
        this.dateSchedule = dateSchedule;
        this.semester = semester;
        this.hometask = hometask;
        this.scheduleCode = scheduleCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleDatesDTO that = (ScheduleDatesDTO) o;
        return semester == that.semester && Objects.equals(dateSchedule, that.dateSchedule) && Objects.equals(hometask, that.hometask) && Objects.equals(scheduleCode, that.scheduleCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateSchedule, semester, hometask, scheduleCode);
    }
}