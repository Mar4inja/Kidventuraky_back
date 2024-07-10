package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<Games, Long> {

   Games findByTitleAndTaskContent(String task, String content);
}
