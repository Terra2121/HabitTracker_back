package com.habit_tracker.Habit.Tracker.mappers;

import com.habit_tracker.Habit.Tracker.dto.UserDTO;
import com.habit_tracker.Habit.Tracker.entities.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

    private final ModelMapper modelMapper;

    public UserDTOMapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
    }

    public UserDTO userToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User userToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
