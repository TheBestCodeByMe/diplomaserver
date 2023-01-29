package com.example.diploma.repo;

import com.example.diploma.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    Calendar findByLessonNumberAndWeekDay(int lessonNumber, int weekDay);
}
