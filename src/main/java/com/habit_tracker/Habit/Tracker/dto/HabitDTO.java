package com.habit_tracker.Habit.Tracker.dto;
import lombok.Data;

@Data
public class HabitDTO {
    private Long habit_id;
    private String name;
    private Integer user_id;

    public Long getHabit_id() {
        return habit_id;
    }

    public void setHabit_id(Long habit_id) {
        this.habit_id = habit_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
