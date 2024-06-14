package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Role;
import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.exceptions.UserIsNotExistException;
import de.ait.project_KidVenture.repository.RoleRepository;
import de.ait.project_KidVenture.repository.UserRepository;
import de.ait.project_KidVenture.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        // Pārbauda, vai lietotāja ID ir null, lai izvairītos no nevēlamas uzstādīšanas
        user.setId(null);

        // Validācija, vai visi obligātie lauki ir aizpildīti
        if (user.getFirstName() == null || user.getFirstName().isEmpty() ||
                user.getLastName() == null || user.getLastName().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Fields first name, last name, email, and password are required");
        }

        // Pārbauda, vai lietotājs ar norādīto e-pasta adresi jau pastāv
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }

        // Uzstāda šifrētu paroli
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Pievieno lietotājam noklusējuma lomu "ROLE_USER"
        Role userRole = roleRepository.findByTitle("ROLE_USER");
        if (userRole == null) {
            throw new RuntimeException("Role ROLE_USER not found in the database");
        }
        user.setRoles(Collections.singleton(userRole));

        // Uzstāda aktīvo statusu
        user.setActive(true);

        // Saglabā lietotāju datubāzē
        User savedUser = userRepository.save(user);
        return savedUser; // Izmanto mapēšanas servisu, lai konvertētu uz UserDto
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id, User updatedUser) {
        // Pārbauda, vai lietotājs ar norādīto ID eksistē datubāzē
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            // Atjauno lietotāja datus ar jauniem datiem
            User existingUser = existingUserOptional.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setAge(updatedUser.getAge()); // If age exists in User entity
            // Saglabā atjaunināto lietotāju un atgriež to
            return userRepository.save(existingUser);
        } else {
            // Ja lietotājs ar norādīto ID netika atrasts, izmet izņēmumu
            throw new IllegalArgumentException("User with ID " + updatedUser.getId() + " not found");
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new UserIsNotExistException("User with id: " + id + " does not exist");
            }
            userRepository.deleteById(id);
        } catch (UserIsNotExistException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserInfo(Authentication authentication) {
        String username = authentication.getName();
        User currentUser = findByEmail(username);

        if (currentUser == null) {
            throw new NoSuchElementException("User not found");
        }

        return (currentUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String username) {
        logger.info("Searching for user with email: {}", username);
        User user = userRepository.findByEmail(username);
        if (user == null) {
            logger.warn("User with email {} not found", username);
        } else {
            logger.info("User with email {} found", username);
        }
        return user;
    }
}
