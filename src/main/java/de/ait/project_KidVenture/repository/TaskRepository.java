package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
