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


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public User saveUser(User user) {
        user.setId(null);
        // Устанавливаем ID в null, чтобы гарантировать создание нового пользователя
        user.setId(null);

        // Проверяем, что все обязательные поля заполнены
        if (user.getFirstName() == null || user.getFirstName().isEmpty() ||
                user.getLastName() == null || user.getLastName().isEmpty() ||
                user.getAge() == null || user.getAge() < 0 ||
                user.getGender() == null || user.getGender().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Поля имя, фамилия, email и пароль обязательны для заполнения");
        }

        // Проверяем, что пользователь с данным email еще не существует
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        // Устанавливаем роль по умолчанию "ROLE_USER"
        user.setRoles(Collections.singleton(roleRepository.findByTitle("ROLE_USER")));

        // Шифруем пароль пользователя
        user.setPassword(encoder.encode(user.getPassword()));

        // Устанавливаем статус пользователя как активный
        user.setActive(true);

        // Сохраняем пользователя в базе данных
        User savedUser = userRepository.save(user);

        // Логируем успешную регистрацию пользователя
        logger.info("Пользователь успешно зарегистрирован с email: " + user.getEmail());

        // Возвращаем сохраненного пользователя
        return savedUser;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Authentication authentication, User updatedUser) {

        User currentUser = findByEmail(authentication.getName());
        if (currentUser == null) {
            throw new IllegalArgumentException("User is not found");
        }

        Long id = currentUser.getId();

        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setAge(updatedUser.getAge());
            existingUser.setGender(updatedUser.getGender());
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
