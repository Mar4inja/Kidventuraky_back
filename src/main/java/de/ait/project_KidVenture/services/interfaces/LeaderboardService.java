package de.ait.project_KidVenture.services.interfaces;

import de.ait.project_KidVenture.entity.Leaderboard;

import java.util.List;

public interface LeaderboardService {

    Leaderboard createLeaderboard(Long taskId, Long userId, int score);

    // Get all leaderboard entries
    List<String> getAllEntries();

    // Update the leaderboard with user results
    Leaderboard updateLeaderboardWithResults(Long taskId, Long userId, int score);

    // Delete a leaderboard entry by ID
    void deleteLeaderboardEntry(Long id);


}