package de.ait.project_KidVenture.services.interfaces;

import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.entity.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    List<UserDto> getAll();

    UserDto update(Long id, User updatetedUser);

    void deleteById(Long id);

    UserDto getInfo(UserDto userDto);

    Optional<UserDto> findById(Long id);

    UserDto findByEmail(String username);
}
