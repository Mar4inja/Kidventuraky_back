package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}
