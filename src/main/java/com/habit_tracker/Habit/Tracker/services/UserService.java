package com.habit_tracker.Habit.Tracker.services;

import com.habit_tracker.Habit.Tracker.dto.UserDTO;
import com.habit_tracker.Habit.Tracker.entities.User;
import com.habit_tracker.Habit.Tracker.mappers.UserDTOMapper;
import com.habit_tracker.Habit.Tracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserDTOMapper userDTOMapper;

    public UserDTO createUser(UserDTO userDTO) {
        User user = userDTOMapper.userToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userDTOMapper.userToDTO(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userDTOMapper::userToDTO)
                .collect(Collectors.toList());
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}

