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
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public Leaderboard createLeaderboard(Long taskId, Long userId, int score) {
        Leaderboard leaderboard = new Leaderboard();

        // Iestatiet task_id un user_id, lai ierakstītu atbilstošos ierakstus
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + taskId));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        leaderboard.setTask(task);
        leaderboard.setUser(user);

        // Iegūstiet visus lietotājus, sakārtojot tos pēc rezultātiem dilstošā secībā
        List<User> usersByScore = userRepository.findAllByOrderByScoreDesc();

        // Atrodiet lietotāju rangu un iestatiet to leaderboard ierakstā
        int rank = findRank(usersByScore, user);
        leaderboard.setRank(rank);

        // Iestatiet rezultāta vērtību
        leaderboard.setScore(score);

        // Saglabājiet jauno leaderboard ierakstu un atgrieziet rezultātu
        return leaderboardRepository.save(leaderboard);
    }

    // Metode, kas atrod lietotāja rangu sakārtotajā sarakstā pēc rezultātiem
    private int findRank(List<User> usersByScore, User user) {
        int rank = 1;
        for (User u : usersByScore) {
            if (u.equals(user)) {
                return rank; // Ja atrodam lietotāju, atgriežam viņa rangu
            }
            rank++;
        }
        return rank; // Ja lietotājs nav atrasts, atgriežam viņa aizliegto rangu
    }

    @Override
    public void deleteLeaderboardEntry(Long id) {
        if (leaderboardRepository.existsById(id)) {
            leaderboardRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Entry with id: " + id + "is not found!!");
        }
    }


    @Override
    public Leaderboard updateLeaderboardWithResults(Long taskId, Long userId, int score) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Leaderboard leaderboard = leaderboardRepository.findByTaskIdAndUserId(taskId, userId)
                .orElse(new Leaderboard());

        leaderboard.setTask(task);
        leaderboard.setUser(user);
        leaderboard.setScore(score);
        leaderboardRepository.save(leaderboard);

        // Update leaderboard rankings based on scores
        updateRankings(taskId);

        return leaderboard;
    }

    private void updateRankings(Long taskId) {
        List<Leaderboard> leaderboards = leaderboardRepository.findByTaskIdOrderByScoreDesc(taskId);
        int rank = 1;
        for (Leaderboard leaderboard : leaderboards) {
            leaderboard.setRank(rank++);
            leaderboardRepository.save(leaderboard);
        }
    }

    @Override
    public List<String> getAllEntries() {
        List<Leaderboard> leaderboardEntries = leaderboardRepository.findAll();
        Map<Long, Integer> userScores = new HashMap<>();
        List<String> entries = new ArrayList<>();

        for (Leaderboard entry : leaderboardEntries) {
            Long userId = entry.getUser().getId();
            Integer score = entry.getScore();
            if (userScores.containsKey(userId)) {
                Integer existingScore = userScores.get(userId);
                if (score > existingScore) {
                    userScores.put(userId, score);
                }
            } else {
                userScores.put(userId, score);
            }
        }

        List<Map.Entry<Long, Integer>> sortedEntries = userScores.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        int rank = 1;
        for (Map.Entry<Long, Integer> entry : sortedEntries) {
            // Piekļūstiet lietotāja informācijai, lai iegūtu vārdu un uzvārdu
            User user = userRepository.findById(entry.getKey()).orElse(null);
            if (user != null) {
                String userName = user.getFirstName();
                String userEntry = String.format("%d. %s --> %d", rank++, userName, entry.getValue());
                entries.add(userEntry);
            }
        }
        return entries;
    }
}