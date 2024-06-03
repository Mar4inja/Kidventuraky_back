package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Task;
import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.entity.UserProgress;
import de.ait.project_KidVenture.repository.UserProgressRepository;
import de.ait.project_KidVenture.services.UserProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProgressServiceImpl implements UserProgressService {

    private final UserProgressRepository userProgressRepository;

    @Override
    public UserProgress createUserProgress(User user, Task task) {
        UserProgress progress = UserProgress.builder()
                .user(user)
                .task(task)
                .completedTasks(0)
                .totalScore(0)
                .progressPercentage(0.0)
                .build();
        return userProgressRepository.save(progress);
    }

    @Override
    public Optional<UserProgress> getUserProgressById(Long id) {
        if (userProgressRepository.existsById(id)) {

        }
        return userProgressRepository.findById(id);
    }

    @Override
    public UserProgress updateUserProgress(Long id, UserProgress userProgress) {
        Optional<UserProgress> existingProgress = userProgressRepository.findById(id);
        if (existingProgress.isPresent()) {
            UserProgress progressToUpdate = existingProgress.get();
            progressToUpdate.setCompletedTasks(userProgress.getCompletedTasks());
            progressToUpdate.setTotalScore(userProgress.getTotalScore());
            progressToUpdate.setProgressPercentage(userProgress.getProgressPercentage());
            return userProgressRepository.save(progressToUpdate);
        } else {
            throw new RuntimeException("UserProgress not found");
        }
    }


    @Override
    public void deleteUserProgress(Long id) {
        if (userProgressRepository.existsById(id)) {
            userProgressRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("UserProgress does not exist");
        }
    }

    @Override
    public Optional<UserProgress> getUserProgressForTask(Long userId, Long taskId) {
        Optional<UserProgress> userProgress = userProgressRepository.findByUserIdAndTaskId(userId, taskId);
        if (userProgress.isPresent()) {
        }
        return userProgress;
    }

    @Override
    public UserProgress updateUserProgressForTask(Long userId, Long taskId, int completedTasks, int totalScore, double progressPercentage) {
        Optional<UserProgress> userProgress = userProgressRepository.findByUserIdAndTaskId(userId, taskId);
        if (userProgress.isPresent()) {
            UserProgress progressToUpdate = userProgress.get();
            progressToUpdate.setCompletedTasks(completedTasks);
            progressToUpdate.setTotalScore(totalScore);
            progressToUpdate.setProgressPercentage(progressPercentage);
            return userProgressRepository.save(progressToUpdate);
        } else {
            throw new RuntimeException("UserProgress not found");
        }
    }

    @Override
    public List<UserProgress> getOverallProgressForUser(Long userId) {
        return userProgressRepository.findAllByUserId(userId);
    }

    @Override
    public List<UserProgress> getTopProgressForTask(Long taskId, int topN) {
        return userProgressRepository.findAllByTaskId(taskId);
    }
}
