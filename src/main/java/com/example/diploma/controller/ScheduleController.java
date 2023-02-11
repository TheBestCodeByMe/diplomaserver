package com.example.diploma.controller;

import com.example.diploma.dto.schedule.ScheduleDTO;
import com.example.diploma.exception.ResourceNotFoundException;
import com.example.diploma.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/getScheduleDTOPupil/{userId}/{date}")
    public ResponseEntity<?> getScheduleByIdAndDate(@PathVariable(value = "userId") Long userId, @PathVariable(value = "date") String date) {
        return ResponseEntity.ok(Objects.requireNonNullElse(scheduleService.getScheduleDTOByIdAndDate(userId, date), "Расписания на этот день нет"));
    }
}
