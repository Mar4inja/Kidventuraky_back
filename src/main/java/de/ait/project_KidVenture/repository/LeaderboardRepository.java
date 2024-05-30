package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
}
