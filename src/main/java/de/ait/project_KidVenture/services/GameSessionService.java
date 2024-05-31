package de.ait.project_KidVenture.services;

import de.ait.project_KidVenture.entity.GameSession;

import java.util.List;
import java.util.Optional;

public interface GameSessionService {

    GameSession createGameSession(GameSession gameSession);

    Optional<GameSession> getGameSessionById(Long id);

    GameSession updateGameSession(Long id, GameSession gameSession);

    void deleteGameSession(Long id);

    List<GameSession> getAllGameSessions();

    List<GameSession> getGameSessionsByUserId(Long userId);

    List<GameSession> getGameSessionsByTaskId(Long taskId);
}