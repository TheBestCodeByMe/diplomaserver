package com.example.diploma.controller;

import com.example.diploma.dto.pupil.PupilDTO;
import com.example.diploma.service.PupilService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/pupil")
@RequiredArgsConstructor
public class PupilController {

    private final PupilService pupilService;

    @PostMapping("/getByUserId")
    public PupilDTO getPupilByFIO(@RequestBody String userId) {
        return pupilService.getPupilByFIO(userId);
    }
}
