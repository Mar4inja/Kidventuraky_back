package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.GameSession;
import de.ait.project_KidVenture.entity.Task;
import de.ait.project_KidVenture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {


    List<GameSession> findByUserAndTask(User user, Task task);
}
