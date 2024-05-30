package de.ait.project_KidVenture.services;

import de.ait.project_KidVenture.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getAll();

    User update(Long id, User updatetedUser);

    void delete(Long id);

    User getInfo(User user);
}
