package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Games;
import de.ait.project_KidVenture.repository.GamesRepository;
import de.ait.project_KidVenture.services.interfaces.GamesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl implements GamesService {

    private final GamesRepository gamesRepository;

    @Override
    public List<Games> getAllGames() {
        return gamesRepository.findAll();
    }

    @Override
    public Games getGameById(Long id) {
        Optional<Games> task = gamesRepository.findById(id);
        return task.orElse(null);
    }

    @Override
    public Games createGame(Games games) {
        games.setId(null);

        if (games.getTitle() == null || games.getTitle().isEmpty()
                || games.getGamesDescription() == null || games.getGamesDescription().isEmpty()
                || games.getDifficultyLevel() == null || games.getDifficultyLevel().isEmpty()
                || games.getGamesType() == null || games.getGamesType().isEmpty()
                || games.getGamesContent() == null || games.getGamesContent().isEmpty()
                || games.getCorrectAnswer() == null || games.getCorrectAnswer().isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled");
        }

        Games existingGames = gamesRepository.findByTitleAndGamesContent(games.getTitle(), games.getGamesContent());
        if (existingGames != null) {
            throw new IllegalArgumentException("Games already exists");
        }

        games.setTitle(games.getTitle());
        games.setGamesDescription(games.getGamesDescription());
        games.setDifficultyLevel(games.getDifficultyLevel());
        games.setGamesType(games.getGamesType());
        games.setGamesContent(games.getGamesContent());
        games.setCorrectAnswer(games.getCorrectAnswer());

        Games savedGames = gamesRepository.save(games);
        return savedGames;

    }

    @Override
    public Games updateGame(Games games) {

        // Atrodam esošo uzdevumu pēc id
        Games currentGames = gamesRepository.findById(games.getId()).orElseThrow(() -> new IllegalArgumentException("Games not found"));
        Long id = currentGames.getId();
        // Pārbaudām, vai uzdevums eksistē
        Optional<Games> taskOptional = gamesRepository.findById(id);

        if (taskOptional.isPresent()) {
            Games existingGames = taskOptional.get();
            // Atjauninām laukus tikai tad, ja tie nav null
            if (games.getTitle() != null) {
                existingGames.setTitle(games.getTitle());
            }
            if (games.getGamesDescription() != null) {
                existingGames.setGamesDescription(games.getGamesDescription());
            }
            if (games.getDifficultyLevel() != null) {
                existingGames.setDifficultyLevel(games.getDifficultyLevel());
            }
            if (games.getGamesType() != null) {
                existingGames.setGamesType(games.getGamesType());
            }
            if (games.getGamesContent() != null) {
                existingGames.setGamesContent(games.getGamesContent());
            }
            if (games.getCorrectAnswer() != null) {
                existingGames.setCorrectAnswer(games.getCorrectAnswer());
            }
            // Atjauninām laikus tikai tad, ja tie nav null
            if (games.getCreatedAt() != null) {
                existingGames.setCreatedAt(games.getCreatedAt());
            }
            if (games.getUpdatedAt() != null) {
                existingGames.setUpdatedAt(games.getUpdatedAt());
            }
            // Saglabājam un atgriežam atjaunināto uzdevumu
            return gamesRepository.save(existingGames);
        } else {
            throw new IllegalArgumentException("Games not found");
        }
    }

    @Override
    public void deleteGameById(Long id) {
        if (gamesRepository.findById(id) == null) {
            throw new IllegalArgumentException("Games not found");
        }
        gamesRepository.deleteById(id);
    }

    @Override
    public List<Games> searchGameByDifficulty(String difficulty) {
        return gamesRepository.findAll().stream()
                .filter(task -> task.getDifficultyLevel().equalsIgnoreCase(difficulty))
                .collect(Collectors.toList());
    }

    @Override
    public List<Games> searchByType(String taskType) {
        return gamesRepository.findAll().stream()
                .filter(task -> task.getGamesType().equals(taskType))
                .collect(Collectors.toList());
    }
}

