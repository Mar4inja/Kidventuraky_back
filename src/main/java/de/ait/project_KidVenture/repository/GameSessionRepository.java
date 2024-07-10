package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.GameSession;
import de.ait.project_KidVenture.entity.Games;
import de.ait.project_KidVenture.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {


    List<GameSession> findByUserAndGames(User user, Games games);
}
