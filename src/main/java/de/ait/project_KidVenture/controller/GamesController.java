package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.Games;
import de.ait.project_KidVenture.services.interfaces.GamesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;



@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GamesController {

    private final GamesService gamesService;
    private final RestTemplateBuilder restTemplateBuilder;
    private static final Logger logger = LoggerFactory.getLogger(GamesController.class); // Pareizs Logger inicializējums


    @GetMapping("/getAllGames")
    public ResponseEntity<List<Games>> getAllGames() {
        return ResponseEntity.ok(gamesService.getAllGames());
    }

    @Operation(summary = "Get games by ID")
    @GetMapping("/{gameId}")
    public ResponseEntity<Games> getGameById(@PathVariable(value = "gameId") long id) {
        Games games = gamesService.getGameById(id);
        if (games == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Games not found");
        }
        return ResponseEntity.ok(games);
    }

    @GetMapping("/filter/ageGroup")
    public ResponseEntity<List<Games>> filterGamesByAgeGroup(@RequestParam String ageGroup) {
        List<Games> filteredGames = gamesService.gameFilterByAGeGroup(ageGroup);
        return ResponseEntity.ok(filteredGames);
    }


    @GetMapping("/filter/difficultyLevel")
    public ResponseEntity<List<Games>> filterGamesByDifficultyLevel(@RequestParam String difficultyLevel) {
        List<Games> filteredGames = gamesService.gameFilterByDifficultyLevel(difficultyLevel);
        return ResponseEntity.ok(filteredGames);
    }


    @GetMapping("/findByGameType")
    public ResponseEntity<List<Games>> findByGameType(@RequestParam String gameType) {
        List<Games> gamesByType = gamesService.gameFilterByGameType(gameType);
        return ResponseEntity.ok(gamesByType);
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


    @PostMapping("/filteredGames")
    public ResponseEntity<List<Games>> getFilteredGames(@RequestParam String gameCategory, @RequestParam String ageGroup) {
        // Логика обработки запроса
        // Пример:
        List<Games> games = gamesService.getFilteredGames(gameCategory, ageGroup);
        if (games.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(games);
    }
}