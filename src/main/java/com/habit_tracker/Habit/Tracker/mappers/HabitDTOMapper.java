package com.habit_tracker.Habit.Tracker.mappers;

import com.habit_tracker.Habit.Tracker.dto.HabitDTO;
import com.habit_tracker.Habit.Tracker.entities.Habit;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class HabitDTOMapper {

    private ModelMapper modelMapper;

    public HabitDTOMapper(){
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
    }

    public HabitDTO habitToDTO(Habit habit){
        return modelMapper.map(habit, HabitDTO.class);
    }

    public Habit habitToEntity(HabitDTO habitDTO){
        return modelMapper.map(habitDTO, Habit.class);
    }
}
