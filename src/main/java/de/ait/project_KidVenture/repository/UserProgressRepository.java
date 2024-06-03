package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {

    Optional<UserProgress> findByUserIdAndTaskId(Long userId, Long taskId);

    List<UserProgress> findAllByUserId(Long userId);

    List<UserProgress> findAllByTaskId(Long taskId);
}
