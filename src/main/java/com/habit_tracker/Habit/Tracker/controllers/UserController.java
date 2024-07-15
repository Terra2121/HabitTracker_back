package com.habit_tracker.Habit.Tracker.controllers;
import com.habit_tracker.Habit.Tracker.dto.UserDTO;
import com.habit_tracker.Habit.Tracker.entities.Habit;
import com.habit_tracker.Habit.Tracker.entities.User;

import com.habit_tracker.Habit.Tracker.services.HabitService;
import com.habit_tracker.Habit.Tracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HabitService habitService;

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.createUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/api/users/getByEmail")
    public Optional<User> getUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }
}


