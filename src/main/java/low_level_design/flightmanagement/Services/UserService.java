package low_level_design.flightmanagement.Services;



import low_level_design.flightmanagement.DTOs.UserDto;
import low_level_design.flightmanagement.Models.User;
import low_level_design.flightmanagement.ObjectMappers.DtoMapper;
import low_level_design.flightmanagement.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findByName(String userName) {
        return userRepository.findByName(userName);
    }

    public UserDto saveUser(UserDto userDto) {
        User user = DtoMapper.mapDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return DtoMapper.mapUserToDto(savedUser);
    }

    public UserDto updateUser(Long id, UserDto userUpdates) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userUpdates.getName() != null ? userUpdates.getName() : user.getName());
                    user.setFunds(userUpdates.getFunds() != null ? userUpdates.getFunds() : user.getFunds());
                    User updatedUser = userRepository.save(user);
                    return DtoMapper.mapUserToDto(updatedUser);
                })
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
    }
}