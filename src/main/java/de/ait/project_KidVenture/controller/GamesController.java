package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.Games;
import de.ait.project_KidVenture.services.interfaces.GamesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class GamesController {

    private final GamesService gamesService;
    private final RestTemplateBuilder restTemplateBuilder;

    @Operation(summary = "Get All tasks")
    @GetMapping("/getAllTasks")
    public ResponseEntity<List<Games>> getAllTasks() {
        return ResponseEntity.ok(gamesService.getAllGames());
    }

    @Operation(summary = "Get games by ID")
    @GetMapping("/{taskId}")
    public ResponseEntity<Games> getTaskById(@PathVariable(value = "taskId") long id) {
        Games games = gamesService.getGameById(id);
        if (games == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Games not found");
        }
        return ResponseEntity.ok(games);
    }

    @Operation(summary = "Find games by difficulty. (Low)-(Medium)-(High)")
    @GetMapping("/difficulty")
    public ResponseEntity<List<Games>> getTasksByDifficulty(@RequestParam String difficulty) {
        return ResponseEntity.ok( gamesService.searchGameByDifficulty(difficulty));
    }

    @Operation(summary = "Find games by type (practical)-(theoretical)")
    @GetMapping("/gameType")
    public ResponseEntity<List<Games>> getGamesByType(@RequestParam String gameType) {
        return ResponseEntity.ok(gamesService.searchByType(gameType));
    }

    @Operation(summary = "Create new games", description = "Create a new games with provided details")
    @PostMapping("/create")
    public ResponseEntity<Games> createTask(@RequestBody Games games) {
        Games createdGames = gamesService.createGame(games);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGames);
    }

    @Operation(summary = "Update games")
    @PutMapping("/update/{gameId}")
    public ResponseEntity<Games> updateTask(@PathVariable(value = "gameId") long id, @RequestBody Games games) {
        games.setId(id);
        return ResponseEntity.ok(gamesService.updateGame(games));
    }

    @Operation(summary = "Delete games by ID")
    @DeleteMapping("/delete/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable(value = "gameId") long id) {
       gamesService.deleteGameById(id);
       return ResponseEntity.ok("Games deleted successfully");
    }
}
