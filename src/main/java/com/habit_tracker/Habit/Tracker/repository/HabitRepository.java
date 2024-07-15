package com.habit_tracker.Habit.Tracker.repository;

import com.habit_tracker.Habit.Tracker.entities.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    @Query("SELECT h FROM Habit h WHERE h.user_id = :user_id")
    List<Habit> findByUserId(@Param("user_id") Integer user_id);
}