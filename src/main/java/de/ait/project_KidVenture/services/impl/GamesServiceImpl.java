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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        boolean isAdmin = authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
//
//        if (!isAdmin) {
//            throw new SecurityException("Only admins can add games");
//        }

        games.setId(null);

        if (games.getTitle() == null || games.getTitle().isEmpty()
                || games.getGamesDescription() == null || games.getGamesDescription().isEmpty()
                || games.getDifficultyLevel() == null || games.getDifficultyLevel().isEmpty()
                || games.getGamesType() == null || games.getGamesType().isEmpty()
                || games.getGameCategory() == null || games.getGameCategory().isEmpty()
                || games.getAgeGroup() == null || games.getAgeGroup().isEmpty()
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

        Games currentGames = gamesRepository.findById(games.getId()).orElseThrow(() -> new IllegalArgumentException("Games not found"));
        Long id = currentGames.getId();

        Optional<Games> taskOptional = gamesRepository.findById(id);

        if (taskOptional.isPresent()) {
            Games existingGames = taskOptional.get();
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
            return gamesRepository.save(existingGames);
        } else {
            throw new IllegalArgumentException("Games not found");
        }
    }

    @Override
    public List<Games> showGamesByGameCategoryAndAge(String gameCategory, String ageGroup) {
        if (gameCategory == null || gameCategory.isEmpty() || ageGroup == null || ageGroup.isEmpty()) {
            throw new IllegalArgumentException("Game data-base doesn't contain any games");
        } else {
            return gamesRepository.findByGameCategoryAndAgeGroup(gameCategory, ageGroup);
        }
    }
}
