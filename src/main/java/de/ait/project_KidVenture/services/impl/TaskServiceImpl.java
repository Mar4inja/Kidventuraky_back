package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Task;
import de.ait.project_KidVenture.repository.TaskRepository;
import de.ait.project_KidVenture.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    @Override
    public Task createTask(Task task) {
        task.setId(null);

        if (task.getTitle() == null || task.getTitle().isEmpty()
                || task.getTaskDescription() == null || task.getTaskDescription().isEmpty()
                || task.getDifficultyLevel() == null || task.getDifficultyLevel().isEmpty()
                || task.getTaskType() == null || task.getTaskType().isEmpty()
                || task.getCorrectAnswer() == null || task.getCorrectAnswer().isEmpty()) {

        }

        if (taskRepository.findByTitle(task.getTitle()) != null) {
            throw new IllegalArgumentException("Task already exists");
        }

        task.setTitle(task.getTitle());
        task.setTaskDescription(task.getTaskDescription());
        task.setDifficultyLevel(task.getDifficultyLevel());
        task.setTaskType(task.getTaskType());
        task.setCorrectAnswer(task.getCorrectAnswer());
        Task savedTask = taskRepository.save(task);
        return savedTask;

    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }

    @Override
    public List<Task> searchTaskByDifficulty(String difficulty) {
        return List.of();
    }

    @Override
    public List<Task> searchByType(String taskType) {
        return List.of();
    }
}

//    private String title;
//    private String taskDescription;
//    private String difficultyLevel;
//    private String taskType;
//    private String correctAnswer;