package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findAllByOrderByScoreDesc();
}
