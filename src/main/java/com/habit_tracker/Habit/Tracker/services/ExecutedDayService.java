package com.habit_tracker.Habit.Tracker.services;

import com.habit_tracker.Habit.Tracker.dto.ExecutedDayDTO;
import com.habit_tracker.Habit.Tracker.entities.ExecutedDay;
import com.habit_tracker.Habit.Tracker.mappers.ExecutedDayDTOMapper;
import com.habit_tracker.Habit.Tracker.repository.ExecutedDayRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExecutedDayService {
    private ExecutedDayRepository executedDayRepository;
    private ExecutedDayDTOMapper executedDayDTOMapper;

    public List<ExecutedDayDTO> getAllDays(){
        List<ExecutedDay> executedDays = executedDayRepository.findAll();
        return executedDays.stream()
                .map(executedDayDTOMapper::dayToDTO)
                .collect(Collectors.toList());
    }

    public ExecutedDayDTO createDay(ExecutedDayDTO executedDayDTO){
        ExecutedDay executedDay = executedDayDTOMapper.dayToEntity(executedDayDTO);
        ExecutedDay savedExecutedDay = executedDayRepository.save(executedDay);
        return executedDayDTOMapper.dayToDTO(savedExecutedDay);
    }

    public Optional<ExecutedDay> findById(Long day_id){
        return executedDayRepository.findById(day_id);
    }

    public ExecutedDay save(ExecutedDay executedDay){
        return executedDayRepository.save(executedDay);
    }

    public void deleteDay(Long day_id){
        Optional<ExecutedDay> executedDay = executedDayRepository.findById(day_id);
        if(executedDay.isPresent()){
            executedDayRepository.delete(executedDay.get());
        }else {
            throw new EntityNotFoundException("Executed day not found with id: " + day_id);
        }

    }
}
