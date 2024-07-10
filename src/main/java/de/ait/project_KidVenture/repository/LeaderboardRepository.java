package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {

    Optional<Leaderboard> findByGamesIdAndUserId(Long taskId, Long userId);

    List<Leaderboard> findByGamesIdOrderByScoreDesc(Long taskId);
}