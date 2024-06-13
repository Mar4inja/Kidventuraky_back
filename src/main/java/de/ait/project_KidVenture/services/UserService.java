package de.ait.project_KidVenture.services;

import de.ait.project_KidVenture.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    List<User> getAll();

    User update(Long id, User updatetedUser);

    void deleteById(Long id);

    User getInfo(User user);

    Optional<User> findById(Long id);

    User findByEmail(String username);
}
