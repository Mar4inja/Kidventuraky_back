package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Leaderboard;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    Optional<Leaderboard> findByUserIdAndTaskId(Long userId, Long taskId);

    List<Leaderboard> findTopByTaskIdOrderByRankAsc(Long taskId, PageRequest of);
}