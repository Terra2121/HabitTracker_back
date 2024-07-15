package com.habit_tracker.Habit.Tracker.mappers;

import com.habit_tracker.Habit.Tracker.dto.ExecutedDayDTO;
import com.habit_tracker.Habit.Tracker.entities.ExecutedDay;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;

@Component
public class ExecutedDayDTOMapper {
    private ModelMapper modelMapper;

    public ExecutedDayDTOMapper(){
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
    }

    public ExecutedDayDTO dayToDTO(ExecutedDay executedDay){
        return modelMapper.map(executedDay, ExecutedDayDTO.class);
    }

    public ExecutedDay dayToEntity(ExecutedDayDTO executedDayDTO){
        return modelMapper.map(executedDayDTO, ExecutedDay.class);
    }
}
