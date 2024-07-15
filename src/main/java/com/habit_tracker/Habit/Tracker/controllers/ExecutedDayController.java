package com.habit_tracker.Habit.Tracker.controllers;


import com.habit_tracker.Habit.Tracker.dto.ExecutedDayDTO;
import com.habit_tracker.Habit.Tracker.entities.ExecutedDay;
import com.habit_tracker.Habit.Tracker.mappers.ExecutedDayDTOMapper;
import com.habit_tracker.Habit.Tracker.services.ExecutedDayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/api/executedDays")
public class ExecutedDayController {
    @Autowired
    private ExecutedDayService executedDayService;
    private ExecutedDayDTOMapper executedDayDTOMapper;

    @GetMapping("/getAll")
    public ResponseEntity<List<ExecutedDayDTO>> getAllDays(){
        List<ExecutedDayDTO> executedDayDTOList = executedDayService.getAllDays();
        return new ResponseEntity<>(executedDayDTOList, HttpStatus.OK);
    }

    @PostMapping("/createDay")
    public ResponseEntity<ExecutedDayDTO> createDay(@RequestBody ExecutedDayDTO executedDayDTO){
        ExecutedDayDTO savedDay = executedDayService.createDay(executedDayDTO);
        return new ResponseEntity<>(savedDay, HttpStatus.CREATED);
    }

//    @PutMapping("/updateHabit/{habit_id}")
//    public ResponseEntity<ExecutedDayDTO> updateDay(@RequestBody ExecutedDayDTO updatedExecutedDayDTO, @PathVariable("day_id") Long day_id){
//        Optional<ExecutedDay> existingDayOptional = executedDayService.findById(day_id);
//
//        if(existingDayOptional.isPresent()){
//            ExecutedDay existingDay = existingDayOptional.get();
//            existingDay.setName(updatedHabitDTO.getName());
//            Habit savedHabit = habitService.save(existingHabit);
//            return ResponseEntity.ok(habitDTOMapper.habitToDTO(savedHabit));
//        }else{
//            Habit newHabit = habitDTOMapper.habitToEntity(updatedHabitDTO);
//            newHabit.setHabit_id(habit_id);
//            Habit savedHabit = habitService.save(newHabit);
//            return ResponseEntity.ok(habitDTOMapper.habitToDTO(savedHabit));
//        }
//    }

    @DeleteMapping("/deleteDay/{day_id}")
    public ResponseEntity<Void> deleteDay(@PathVariable("day_id") Long day_id){
        try{
            executedDayService.deleteDay(day_id);
            return ResponseEntity.ok().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
