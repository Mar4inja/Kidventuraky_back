package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.GameSession;
import de.ait.project_KidVenture.entity.Games;
import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.repository.GameSessionRepository;
import de.ait.project_KidVenture.repository.GamesRepository;
import de.ait.project_KidVenture.repository.UserRepository;
import de.ait.project_KidVenture.services.interfaces.GameSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameSessionServiceImp implements GameSessionService {

    private final GameSessionRepository gameSessionRepository;
    private final UserRepository userRepository;
    private final GamesRepository gamesRepository;


    @Override
    public GameSession createGameSession(GameSession gameSession) {

        // Pārbauda, vai lietotājam jau nav sesija ar tādu pašu uzdevumu
        List<GameSession> existingSessions = gameSessionRepository.findByUserAndTask(
                gameSession.getUser(),
                gameSession.getGames());
        if (!existingSessions.isEmpty()) {
            throw new IllegalArgumentException("User already has a session with the same games");
        }

        User user = userRepository.findById(gameSession.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id"));
        Games games = gamesRepository.findById(gameSession.getGames().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid games id"));
        // Create a new game session
        gameSession.setUser(user);
        gameSession.setGames(games);
        gameSession.setStartTime(gameSession.getStartTime());
        gameSession.setEndTime(gameSession.getEndTime());
        gameSession.setScore(gameSession.getScore());

        if (gameSession.getId() != null) {
            throw new IllegalArgumentException("Cannot create a game session with a predefined id");
        }

        if (gameSession.getScore() < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }

        return gameSessionRepository.save(gameSession);
    }


    @Override
    public Optional<GameSession> getGameSessionById(Long id) {
        return gameSessionRepository.findAll().stream()
                .filter(gameSession -> gameSession.getId().equals(id))
                .findFirst();
    }

    @Override
    public GameSession updateGameSession(Long id, GameSession gameSession) {
        Optional<GameSession> optionalGameSession = getGameSessionById(id);

        if (optionalGameSession.isPresent()) {
            GameSession existingGameSession = optionalGameSession.get();

            existingGameSession.setStartTime(gameSession.getStartTime());
            existingGameSession.setEndTime(gameSession.getEndTime());
            existingGameSession.setScore(gameSession.getScore());

            return gameSessionRepository.save(existingGameSession);
        } else {
            throw new IllegalArgumentException("Game sesion with id " + id + " not found");
        }
    }

    @Override
    public void deleteGameSession(Long id) {
        if (!gameSessionRepository.existsById(id)) {
            throw new IllegalArgumentException("Game session with id " + id + " not found");
        }
        gameSessionRepository.deleteById(id);
    }

    @Override
    public List<GameSession> getAllGameSessions() {
        return gameSessionRepository.findAll();
    }

    @Override
    public List<GameSession> getGameSessionsByUserId(Long userId) {
        return gameSessionRepository.findAll().stream()
                .filter(session -> session.getUser() != null && session.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<GameSession> getGameSessionsByTaskId(Long taskId) {
        return gameSessionRepository.findAll().stream()
                .filter(session -> session.getGames() != null && session.getGames().getId().equals(taskId))
                .collect(Collectors.toList());
    }
}
