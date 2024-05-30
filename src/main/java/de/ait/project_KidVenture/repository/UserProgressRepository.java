package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
}
