package com.habit_tracker.Habit.Tracker.repository;

import com.habit_tracker.Habit.Tracker.entities.ExecutedDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutedDayRepository extends JpaRepository<ExecutedDay, Long> {
}
