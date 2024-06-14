package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.GameSession;
import de.ait.project_KidVenture.services.interfaces.GameSessionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gameSessions")
@RequiredArgsConstructor
public class GameSessionController {

    private final GameSessionService gameSessionService;

    @Operation(summary = "Find all game sessions")
    @GetMapping
    public ResponseEntity<List<GameSession>> findAllGameSessions() {
        return ResponseEntity.ok(gameSessionService.getAllGameSessions());
    }

    @Operation(summary = "Find game session by user ID")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GameSession>> findGameSessionByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(gameSessionService.getGameSessionsByUserId(userId));
    }

    @Operation(summary = "Find game session by task ID")
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<GameSession>> findGameSessionByTaskId(@PathVariable Long taskId) {
        return ResponseEntity.ok(gameSessionService.getGameSessionsByTaskId(taskId));
    }

    @Operation(summary = "Create new game session")
    @PostMapping("/create")
    public ResponseEntity<GameSession> createGameSession(@RequestBody GameSession gameSession) {
        return ResponseEntity.ok(gameSessionService.createGameSession(gameSession));
    }

    @Operation(summary = "Find game session by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<GameSession>> findGameSessionById(@PathVariable Long id) {
        return ResponseEntity.ok(gameSessionService.getGameSessionById(id));

    }

    @Operation(summary = "Delete game session by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGameSession(@PathVariable Long id) {
        gameSessionService.deleteGameSession(id);
        return ResponseEntity.ok("Session is deleted successfully");
    }


}


