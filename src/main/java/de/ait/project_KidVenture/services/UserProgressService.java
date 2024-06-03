package de.ait.project_KidVenture.services;

import de.ait.project_KidVenture.entity.Task;
import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.entity.UserProgress;
import java.util.List;
import java.util.Optional;

public interface UserProgressService {


    UserProgress createUserProgress(User user, Task task);

    // Get a user progress entry by ID
    Optional<UserProgress> getUserProgressById(Long id);

    // Update an existing user progress entry
    UserProgress updateUserProgress(Long id, UserProgress userProgress);

    // Delete a user progress entry by ID
    void deleteUserProgress(Long id);

    // Get the progress of a user for a specific task
    Optional<UserProgress> getUserProgressForTask(Long userId, Long taskId);

    // Update the progress of a user for a specific task
    UserProgress updateUserProgressForTask(Long userId, Long taskId, int completedTasks, int totalScore, double progressPercentage);

    // Get the overall progress of a user across all tasks
    List<UserProgress> getOverallProgressForUser(Long userId);

    // Get a list of users with the highest progress for a specific task
    List<UserProgress> getTopProgressForTask(Long taskId, int topN);
}
