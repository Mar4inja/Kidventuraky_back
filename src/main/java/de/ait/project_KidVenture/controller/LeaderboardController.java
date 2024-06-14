package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.Leaderboard;
import de.ait.project_KidVenture.services.interfaces.LeaderboardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboards")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;


    @Operation(summary = "Create a new leaderboard")
    @PostMapping("/create")
    public ResponseEntity<Leaderboard> createLeaderboard(@RequestParam Long taskId, @RequestParam Long userId, @RequestParam int score) {
        Leaderboard leaderboard = leaderboardService.createLeaderboard(taskId, userId, score);
        return new ResponseEntity<>(leaderboard, HttpStatus.CREATED);
    }


    @Operation(summary = "Update the leaderboard with user results")
    @PostMapping("/update")
    public ResponseEntity<Leaderboard> updateLeaderboardWithResults(@RequestParam Long taskId, @RequestParam Long userId, @RequestParam int score) {
        Leaderboard leaderboard = leaderboardService.updateLeaderboardWithResults(taskId, userId, score);
        return new ResponseEntity<>(leaderboard, HttpStatus.OK);
    }

    @Operation(summary = "Get all leaderboard entries")
    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllEntries() {
        List<String> leaderboards = leaderboardService.getAllEntries();
        return new ResponseEntity<>(leaderboards, HttpStatus.OK);
    }


    @Operation(summary = "Delete a leaderboard entry by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaderboardEntry(@PathVariable Long id) {
        leaderboardService.deleteLeaderboardEntry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
