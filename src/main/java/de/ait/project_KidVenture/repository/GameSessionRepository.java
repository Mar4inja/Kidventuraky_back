package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
}
