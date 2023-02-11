package com.example.diploma.dao;

import com.example.diploma.model.Calendar;
import com.example.diploma.model.Subject;
import com.example.diploma.model.Teacher;
import com.example.diploma.repo.CalendarRepository;
import com.example.diploma.repo.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalendarDao {

    private final CalendarRepository calendarRepository;

    public Calendar findByLessonNumberAndWeekDay(Integer lessonNumber, Integer weekDay) {
        return calendarRepository.findByLessonNumberAndWeekDay(lessonNumber, weekDay);
    }

    public Calendar getById(Long id) {
        return calendarRepository.getById(id);
    }
}
