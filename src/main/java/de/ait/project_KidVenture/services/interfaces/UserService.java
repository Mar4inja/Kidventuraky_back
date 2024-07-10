package de.ait.project_KidVenture.services.interfaces;

import de.ait.project_KidVenture.entity.User;
import org.springframework.security.core.Authentication;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    boolean emailExists(final String email);

    List<User> getAll();

    User update(Authentication authentication, User updatetedUser);

    void deleteUser(Authentication authentication);

    User getUserInfo(Authentication authentication);

    Optional<User> findById(Long id);

    User findByEmail(String username);
}
