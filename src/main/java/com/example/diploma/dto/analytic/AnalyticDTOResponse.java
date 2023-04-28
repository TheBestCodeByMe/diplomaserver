package com.example.diploma.dto.analytic;

import com.example.diploma.dto.diary.DiaryBySubjectDTOList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AnalyticDTOResponse {
    private List<AcademicPerformanceInClassDTO> academicPerformanceInClassDTOList;
    private List<AcademicPerformanceInMonthDTO> academicPerformanceInMonthDTOList;
    private List<AttendanceInMonthDTO> attendanceInMonthDTOList;

    public AnalyticDTOResponse(List<AcademicPerformanceInClassDTO> academicPerformanceInClassDTOList, List<AcademicPerformanceInMonthDTO> academicPerformanceInMonthDTOList, List<AttendanceInMonthDTO> attendanceInMonthDTOList) {
        this.academicPerformanceInClassDTOList = academicPerformanceInClassDTOList;
        this.academicPerformanceInMonthDTOList = academicPerformanceInMonthDTOList;
        this.attendanceInMonthDTOList = attendanceInMonthDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyticDTOResponse that = (AnalyticDTOResponse) o;
        return Objects.equals(academicPerformanceInClassDTOList, that.academicPerformanceInClassDTOList) && Objects.equals(academicPerformanceInMonthDTOList, that.academicPerformanceInMonthDTOList) && Objects.equals(attendanceInMonthDTOList, that.attendanceInMonthDTOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(academicPerformanceInClassDTOList, academicPerformanceInMonthDTOList, attendanceInMonthDTOList);
    }
}
