package com.habit_tracker.Habit.Tracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExecutedDayDTO {
    private Long executed_day_id;
    private Integer habit_id;
    private LocalDate executed_day;

    public Long getExecuted_day_id() {
        return executed_day_id;
    }

    public void setExecuted_day_id(Long executed_day_id) {
        this.executed_day_id = executed_day_id;
    }

    public Integer getHabit_id() {
        return habit_id;
    }

    public void setHabit_id(Integer habit_id) {
        this.habit_id = habit_id;
    }

    public LocalDate getExecuted_day() {
        return executed_day;
    }

    public void setExecuted_day(LocalDate executed_day) {
        this.executed_day = executed_day;
    }
}
