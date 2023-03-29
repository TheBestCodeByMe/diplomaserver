package com.example.diploma.service;

import com.example.diploma.dto.schedule.ScheduleDatesDTO;
import com.example.diploma.dto.schedule.ScheduleDTO;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDTO> getScheduleDTOByIdAndDate(Long userId, String date);

    List<ScheduleDatesDTO> getDatesBySubjectAndClass(String subject, String classname, Long userId, int semesterId);
}
