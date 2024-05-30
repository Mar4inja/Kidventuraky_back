package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.exceptions.UserIsNotExistException;
import de.ait.project_KidVenture.repository.UserRepository;
import de.ait.project_KidVenture.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        user.setId(null);

        if (user.getName() == null || user.getAge() == null || user.getEmail() == null || user.getRegistrationDate() == null) {
            throw new IllegalArgumentException("All fields are required");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id, User updatedUser) {
        // Pārbauda, vai bērns ar norādīto ID eksistē datu bāzē
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            // Atjauno bērna datus ar jaunajiem datiem
            User existingUser = existingUserOptional.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setAge(updatedUser.getAge());
            // Saglabā atjaunināto bērnu un atgriež to
            return userRepository.save(existingUser);
        } else {
            // Ja bērns ar norādīto ID netika atrasts, izmet izņēmumu
            throw new IllegalArgumentException("User with ID " + updatedUser.getId() + " not found");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new UserIsNotExistException("Person with id: " + id + " does not exist");
            }
            userRepository.deleteById(id);
        } catch (UserIsNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getInfo(User user) {
        String userName = user.getName();
        User currentUser = userRepository.findByEmail(userName);

        if (currentUser == null) {
            try {
                throw new NoSuchFieldException("User is not found");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        return currentUser;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
