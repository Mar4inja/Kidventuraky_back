package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.Leaderboard;
import de.ait.project_KidVenture.services.LeaderboardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leaderboards")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @Operation(summary = "Create a new empty leaderboard")
    @PostMapping("/create")
    public ResponseEntity<Leaderboard> createLeaderboard(@PathVariable Long taskId, @PathVariable Long userId) {
        Leaderboard leaderboardEntry = leaderboardService.createLeaderboardEntry(taskId, userId);
        return new ResponseEntity<>(leaderboardEntry, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a leaderboard entry by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Leaderboard>> getLeaderboard(@PathVariable Long id) {
        Optional<Leaderboard> returnedLeaderboard = leaderboardService.getLeaderboardEntryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(returnedLeaderboard);
    }

    @Operation(summary = "Update a leaderboard entry by ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<Leaderboard> updateLeaderboard(@PathVariable Long id, @RequestBody Leaderboard leaderboard) {
        return ResponseEntity.status(HttpStatus.OK).body(leaderboardService.updateLeaderboardEntry(id, leaderboard));
    }

    @Operation(summary = "Delete a leaderboard entry by ID")
    @DeleteMapping("/{id}")
    public void deleteLeaderboard(@PathVariable Long id) {
        leaderboardService.deleteLeaderboardEntry(id);
    }

    @Operation(summary = "Get the rank of a user for a specific task")
    @GetMapping("/rank/{taskId}/{userId}")
    public ResponseEntity<Optional<Leaderboard>> getUserRankForTask(@PathVariable Long taskId, @PathVariable Long userId) {
        Optional<Leaderboard> rank = leaderboardService.getUserRankForTask(taskId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(rank);
    }

    @Operation(summary = "Get the top users for a specific task")
    @GetMapping("/topUsers/{taskId}")
    public ResponseEntity<List<Leaderboard>> getTopUsersForTask(@PathVariable Long taskId, @PathVariable Long userId) {
        List<Leaderboard> topUsers = leaderboardService.getTopUsersForTask(taskId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(topUsers);
    }

    @Operation(summary = "Update a user's rank and score for a specific task")
    @PutMapping("/users/update/{taskId}/{userId}/{rank}/{score}")
    public ResponseEntity<Leaderboard> updateUserRankAndScore(@PathVariable Long taskId, @PathVariable Long userId, @PathVariable int rank, @PathVariable int score) {
        Leaderboard leaderboard = leaderboardService.updateUserRankAndScore(taskId, userId, rank, score);
        return ResponseEntity.status(HttpStatus.OK).body(leaderboard);
    }

    @Operation(summary = "Get all leaderboard entries for a specific task")
    @GetMapping("/task/{taskId}/entries")
    public ResponseEntity<List<Leaderboard>> getAllEntriesForTask(@PathVariable Long taskId) {
        List<Leaderboard> allEntriesForTask = leaderboardService.getAllEntriesForTask(taskId);
        return ResponseEntity.ok(allEntriesForTask);
    }

    @Operation(summary = "Add a user to a specific leaderboard")
    @PostMapping("/{leaderboardId}/addUser/{userId}")
    public ResponseEntity<Leaderboard> addUserToLeaderboard(@PathVariable Long leaderboardId, @PathVariable Long userId) {
        Leaderboard updatedLeaderboard = leaderboardService.addUserToLeaderboard(leaderboardId, userId);
        return new ResponseEntity<>(updatedLeaderboard, HttpStatus.OK);
    }

    @Operation(summary = "Add a task to a specific leaderboard")
    @PostMapping("/{leaderboardId}/addTask/{taskId}")
    public ResponseEntity<Leaderboard> addTaskToLeaderboard(@PathVariable Long leaderboardId, @PathVariable Long taskId) {
        Leaderboard updatedLeaderboard = leaderboardService.addTaskToLeaderboard(leaderboardId, taskId);
        return new ResponseEntity<>(updatedLeaderboard, HttpStatus.OK);
    }
}
