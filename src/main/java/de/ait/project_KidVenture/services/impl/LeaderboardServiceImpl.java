package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Leaderboard;
import de.ait.project_KidVenture.entity.Task;
import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.repository.LeaderboardRepository;
import de.ait.project_KidVenture.repository.TaskRepository;
import de.ait.project_KidVenture.repository.UserRepository;
import de.ait.project_KidVenture.services.LeaderboardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public Leaderboard createLeaderboardEntry(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setTask(task);
        leaderboard.setUser(user);

        // Iestatiet jebkuru noklusējuma vērtību jūsu ierakstam, piemēram, rangu un rezultātu
        leaderboard.setRank(1);
        leaderboard.setScore(0);

        return leaderboardRepository.save(leaderboard);
    }

    @Override
    public Optional<Leaderboard> getLeaderboardEntryById(Long id) {
        return leaderboardRepository.findById(id);
    }

    @Override
    public Leaderboard updateLeaderboardEntry(Long id, Leaderboard leaderboard) {
        Optional<Leaderboard> existingEntry = leaderboardRepository.findById(id);
        if (existingEntry.isPresent()) {
            leaderboard.setId(id);
            return leaderboardRepository.save(leaderboard);
        } else {
            throw new EntityNotFoundException("Leaderboard entry not found");
        }
    }

    @Override
    public void deleteLeaderboardEntry(Long id) {
        if (leaderboardRepository.existsById(id)) {
            leaderboardRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Leaderboard entry not found");
        }
    }

    @Override
    public Optional<Leaderboard> getUserRankForTask(Long userId, Long taskId) {
        return leaderboardRepository.findByUserIdAndTaskId(userId, taskId);
    }

    @Override
    public List<Leaderboard> getTopUsersForTask(Long taskId, Long userId) {
        Optional<Leaderboard> leaderboard = leaderboardRepository.findByUserIdAndTaskId(userId, taskId);
        if (leaderboard.isPresent()) {
            return leaderboardRepository.findTopByTaskIdOrderByRankAsc(taskId, PageRequest.of(0, 10));
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Leaderboard updateUserRankAndScore(Long userId, Long taskId, int rank, int score) {
        return null; // Šī metode vēl nav implementēta
    }

    @Override
    public List<Leaderboard> getAllEntriesForTask(Long taskId) {
        return leaderboardRepository.findAll();
    }

    public Leaderboard addUserToLeaderboard(Long leaderboardId, Long userId) {
        Leaderboard leaderboard = leaderboardRepository.findById(leaderboardId)
                .orElseThrow(() -> new EntityNotFoundException("Leaderboard not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        leaderboard.setUser(user);
        return leaderboardRepository.save(leaderboard);
    }

    public Leaderboard addTaskToLeaderboard(Long leaderboardId, Long taskId) {
        Leaderboard leaderboard = leaderboardRepository.findById(leaderboardId)
                .orElseThrow(() -> new EntityNotFoundException("Leaderboard not found"));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        leaderboard.setTask(task);
        return leaderboardRepository.save(leaderboard);
    }
}
