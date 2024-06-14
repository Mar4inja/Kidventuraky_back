package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.entity.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDto findByEmail(String email);

    List<UserDto> findAllByOrderByScoreDesc();
}
