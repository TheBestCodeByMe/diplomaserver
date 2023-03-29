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
    private LocalDate date;
    private int semester;
    private String hometask;
    private String scheduleCode;

    public ScheduleDatesDTO(LocalDate date, int semester, String hometask, String scheduleCode) {
        this.date = date;
        this.semester = semester;
        this.hometask = hometask;
        this.scheduleCode = scheduleCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleDatesDTO that = (ScheduleDatesDTO) o;
        return semester == that.semester && Objects.equals(date, that.date) && Objects.equals(hometask, that.hometask) && Objects.equals(scheduleCode, that.scheduleCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, semester, hometask, scheduleCode);
    }
}