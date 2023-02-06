package com.example.diploma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ScheduleController {
/*
    private final SheduleService sheduleService;

    @GetMapping("/getScheduleDTOPupil/{userId}/{date}")
    public ResponseEntity<List<SheduleDTO>> getScheduleByIdAndDate(@PathVariable(value = "userId") Long userId, @PathVariable(value = "date") String date)
            throws ResourceNotFoundException {
        return sheduleService.getScheduleByIdAndDate(userId, date);
    }*/
}
