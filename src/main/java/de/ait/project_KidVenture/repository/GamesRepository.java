package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Games;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamesRepository extends JpaRepository<Games, Long> {

   // Piemērs metodes, kas meklē spēles pēc nosaukuma un spēles satura
   Games findByTitleAndGamesContent(String title, String gamesContent);

   // Piemērs metodes, kas meklē spēles pēc vecuma grupas, grūtības līmeņa un spēles veida
   List<Games> findByAgeGroupAndDifficultyLevelAndGamesType(String ageGroup, String difficultyLevel, String gamesType);

   // Jaunas metodes filtrēšanai
   List<Games> findByCategory(String category);

   List<Games> findByAge(Integer age);

   List<Games> findByCategoryAndAge(String category, Integer age);

   List<Games> findByAgeGroup(String ageGroup);

   List<Games> findByDifficultyLevel(String difficultyLevel);

   List<Games> findByGameType(String gameType);
}
