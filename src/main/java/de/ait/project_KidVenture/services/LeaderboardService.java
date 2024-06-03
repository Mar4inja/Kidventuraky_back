package de.ait.project_KidVenture.services;

import de.ait.project_KidVenture.entity.Leaderboard;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface LeaderboardService {
    // Create a new leaderboard entry
    Leaderboard createLeaderboardEntry(Leaderboard leaderboard);

    // Get a leaderboard entry by ID
    Optional<Leaderboard> getLeaderboardEntryById(Long id);

    // Update an existing leaderboard entry
    Leaderboard updateLeaderboardEntry(Long id, Leaderboard leaderboard);

    // Delete a leaderboard entry by ID
    void deleteLeaderboardEntry(Long id);

    // Get the rank of a user for a specific task
    Optional<Leaderboard> getUserRankForTask(Long userId, Long taskId);


    List<Leaderboard> getTopUsersForTask(Long taskId, Long userId);

    // Update the rank and score of a user for a specific task
    Leaderboard updateUserRankAndScore(Long userId, Long taskId, int rank, int score);

    // Get all leaderboard entries for a specific task
    List<Leaderboard> getAllEntriesForTask(Long taskId);
}


