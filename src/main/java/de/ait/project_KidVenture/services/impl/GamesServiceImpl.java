package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Games;
import de.ait.project_KidVenture.repository.GamesRepository;
import de.ait.project_KidVenture.repository.UserRepository;
import de.ait.project_KidVenture.services.interfaces.GamesService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl implements GamesService {

    private final GamesRepository gamesRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(GamesServiceImpl.class);

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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            throw new SecurityException("Only admins can add games");
        }

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
            // Saglabājam un atgriežam atjaunināto uzdevumu
            return gamesRepository.save(existingGames);
        } else {
            throw new IllegalArgumentException("Games not found");
        }
    }


    public List<Games> gameFilterByAGeGroup(String ageGroup) {
        return gamesRepository.findAll().stream()
                .filter(games -> ageGroup == null || games.getAgeGroup().equalsIgnoreCase(ageGroup))
                .collect(Collectors.toList());
    }

    @Override
    public List<Games> gameFilterByDifficultyLevel(String difficultyLevel) {
        return gamesRepository.findAll().stream()
                .filter(games -> games.getDifficultyLevel() == null || games.getDifficultyLevel().equalsIgnoreCase(difficultyLevel))
                .collect(Collectors.toList());
    }

    @Override
    public List<Games> gameFilterByGameType(String gameType) {
        return gamesRepository.findAll().stream()
                .filter(games -> games.getGamesType() == null || games.getGamesType().equalsIgnoreCase(gameType))
                .collect(Collectors.toList());
    }

    @Override
    public List<Games> getFilteredGames(String gameCategory, String ageGroup) {
        logger.debug("Filtering games with category: {} and age group: {}", gameCategory, ageGroup);

        if (gameCategory != null && ageGroup != null) {
            return gamesRepository.findByGameCategoryAndAgeGroup(gameCategory, ageGroup);
        } else if (gameCategory != null) {
            return gamesRepository.findByGameCategory(gameCategory);
        } else if (ageGroup != null) {
            return gamesRepository.findByAgeGroup(ageGroup);
        } else {
            return gamesRepository.findAll();
        }
    }
}
