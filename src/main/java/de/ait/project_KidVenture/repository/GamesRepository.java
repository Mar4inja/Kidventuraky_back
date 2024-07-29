package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamesRepository extends JpaRepository<Games, Long> {

   Games findByTitleAndGamesContent(String title, String gamesContent);

   List<Games> findByGameCategoryAndAgeGroup(String gameCategory, String ageGroup);



}
