package com.habit_tracker.Habit.Tracker.services;

import com.habit_tracker.Habit.Tracker.dto.HabitDTO;
import com.habit_tracker.Habit.Tracker.entities.Habit;
import com.habit_tracker.Habit.Tracker.mappers.HabitDTOMapper;
import com.habit_tracker.Habit.Tracker.repository.HabitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HabitService {

    private HabitRepository habitRepository;
    private HabitDTOMapper habitDTOMapper;

    public List<HabitDTO> getAllHabits(){
        List<Habit> habits = habitRepository.findAll();
        return habits.stream()
                .map(habitDTOMapper::habitToDTO)
                .collect(Collectors.toList());
    }

    public HabitDTO createHabit(HabitDTO habitDTO){
        Habit habit = habitDTOMapper.habitToEntity(habitDTO);
        Habit savedHabit = habitRepository.save(habit);
        return habitDTOMapper.habitToDTO(savedHabit);
    }

    public Optional<Habit> findById(Long habit_id){
        return habitRepository.findById(habit_id);
    }

    public Habit save(Habit habit){
        return habitRepository.save(habit);
    }

    public void deleteHabit(Long habit_id){
        Optional<Habit> habit = habitRepository.findById(habit_id);
        if(habit.isPresent()){
            habitRepository.delete(habit.get());
        }else {
            throw new EntityNotFoundException("Habit not found with id: " + habit_id);
        }

    }

    public List<Habit> findByUser_id(Integer user_id) {
        return habitRepository.findByUserId(user_id);
    }
}
