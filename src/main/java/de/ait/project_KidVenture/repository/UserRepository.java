package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    List<User> findAllByOrderByScoreDesc();
}
