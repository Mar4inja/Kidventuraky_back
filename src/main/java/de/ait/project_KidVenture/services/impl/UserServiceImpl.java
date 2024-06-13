package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.exceptions.UserIsNotExistException;
import de.ait.project_KidVenture.repository.RoleRepository;
import de.ait.project_KidVenture.repository.UserRepository;
import de.ait.project_KidVenture.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {

        user.setId(null);

        if (user.getFirstName() == null || user.getFirstName().isEmpty() ||
                user.getLastName() == null || user.getLastName().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Fields first name, e-mail and password are required");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }

        user.setRoles(Collections.singleton(roleRepository.findByTitle("ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        User savedUser = userRepository.save(user);
//        emailService.sendConfirmationEmail(user);

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
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setAge(updatedUser.getAge());
            // Saglabā atjaunināto bērnu un atgriež to
            return userRepository.save(existingUser);
        } else {
            // Ja bērns ar norādīto ID netika atrasts, izmet izņēmumu
            throw new IllegalArgumentException("User with ID " + updatedUser.getId() + " not found");
        }
    }

    @Override
    public void deleteById(Long id) {
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
        String userName = user.getFirstName();
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

    @Override
    public User findByEmail(String username) {
        if (userRepository.findByEmail(username) == null) {
            return null;
        } else {
            return userRepository.findByEmail(username);
        }
    }
}
