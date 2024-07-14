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
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GamesController {

    private final GamesService gamesService;
    private final RestTemplateBuilder restTemplateBuilder;

    @Operation(summary = "Get All games")
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

    @Operation(summary = "Filter games by ageGroup, Difficulty, type")
    @GetMapping("/filter")
    public ResponseEntity<List<Games>> filterGamesByDifficulty(@RequestParam String ageGroup, String difficulty, String gameType) {
        return ResponseEntity.ok( gamesService.gameFilter(ageGroup, difficulty, gameType));
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
