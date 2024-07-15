package com.habit_tracker.Habit.Tracker.controllers;

import com.habit_tracker.Habit.Tracker.dto.HabitDTO;
import com.habit_tracker.Habit.Tracker.entities.Habit;
import com.habit_tracker.Habit.Tracker.mappers.HabitDTOMapper;
import com.habit_tracker.Habit.Tracker.services.HabitService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/api/habits")
public class HabitController {
    @Autowired
    private HabitService habitService;
    private HabitDTOMapper habitDTOMapper;

    @GetMapping("/getAll")
    public ResponseEntity<List<HabitDTO>> getAllHabits(){
        List<HabitDTO> habits = habitService.getAllHabits();
        return new ResponseEntity<>(habits, HttpStatus.OK);
    }

    @PostMapping("/createHabit")
    public ResponseEntity<HabitDTO> createHabit(@RequestBody HabitDTO habitDTO){
        HabitDTO savedHabit = habitService.createHabit(habitDTO);
        return new ResponseEntity<>(savedHabit, HttpStatus.CREATED);
    }

    @PutMapping("/updateHabit/{habit_id}")
    public ResponseEntity<HabitDTO> updateHabit(@RequestBody HabitDTO updatedHabitDTO, @PathVariable("habit_id") Long habit_id){
        Optional<Habit> existingHabitOptional = habitService.findById(habit_id);

        if(existingHabitOptional.isPresent()){
            Habit existingHabit = existingHabitOptional.get();
            existingHabit.setName(updatedHabitDTO.getName());
            Habit savedHabit = habitService.save(existingHabit);
            return ResponseEntity.ok(habitDTOMapper.habitToDTO(savedHabit));
        }else{
            Habit newHabit = habitDTOMapper.habitToEntity(updatedHabitDTO);
            newHabit.setHabit_id(habit_id);
            Habit savedHabit = habitService.save(newHabit);
            return ResponseEntity.ok(habitDTOMapper.habitToDTO(savedHabit));
        }
    }

    @DeleteMapping("/deleteHabit/{habit_id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable("habit_id") Long habit_id){
        try{
            habitService.deleteHabit(habit_id);
            return ResponseEntity.ok().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getByUserId")
    public List<Habit> getHabitsByUserId(@RequestParam("user_id") Integer user_id) {
        return habitService.findByUser_id(user_id);
    }
}
