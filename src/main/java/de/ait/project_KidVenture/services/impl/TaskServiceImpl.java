package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Task;
import de.ait.project_KidVenture.repository.TaskRepository;
import de.ait.project_KidVenture.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                || task.getTaskContent() == null || task.getTaskContent().isEmpty()
                || task.getCorrectAnswer() == null || task.getCorrectAnswer().isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled");
        }

        Task existingTask = taskRepository.findByTitleAndTaskContent(task.getTitle(), task.getTaskContent());
        if (existingTask != null) {
            throw new IllegalArgumentException("Task already exists");
        }

        task.setTitle(task.getTitle());
        task.setTaskDescription(task.getTaskDescription());
        task.setDifficultyLevel(task.getDifficultyLevel());
        task.setTaskType(task.getTaskType());
        task.setTaskContent(task.getTaskContent());
        task.setCorrectAnswer(task.getCorrectAnswer());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);
        return savedTask;

    }

    @Override
    public Task updateTask(Task task) {

        // Atrodam esošo uzdevumu pēc id
        Task currentTask = taskRepository.findById(task.getId()).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        Long id = currentTask.getId();
        // Pārbaudām, vai uzdevums eksistē
        Optional<Task> taskOptional = taskRepository.findById(id);

        if (taskOptional.isPresent()) {
            Task existingTask = taskOptional.get();
            // Atjauninām laukus tikai tad, ja tie nav null
            if (task.getTitle() != null) {
                existingTask.setTitle(task.getTitle());
            }
            if (task.getTaskDescription() != null) {
                existingTask.setTaskDescription(task.getTaskDescription());
            }
            if (task.getDifficultyLevel() != null) {
                existingTask.setDifficultyLevel(task.getDifficultyLevel());
            }
            if (task.getTaskType() != null) {
                existingTask.setTaskType(task.getTaskType());
            }
            if (task.getTaskContent() != null) {
                existingTask.setTaskContent(task.getTaskContent());
            }
            if (task.getCorrectAnswer() != null) {
                existingTask.setCorrectAnswer(task.getCorrectAnswer());
            }
            // Atjauninām laikus tikai tad, ja tie nav null
            if (task.getCreatedAt() != null) {
                existingTask.setCreatedAt(task.getCreatedAt());
            }
            if (task.getUpdatedAt() != null) {
                existingTask.setUpdatedAt(task.getUpdatedAt());
            }
            // Saglabājam un atgriežam atjaunināto uzdevumu
            return taskRepository.save(existingTask);
        } else {
            throw new IllegalArgumentException("Task not found");
        }
    }

    @Override
    public void deleteTaskById(Long id) {
        if (taskRepository.findById(id) == null) {
            throw new IllegalArgumentException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> searchTaskByDifficulty(String difficulty) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getDifficultyLevel().equalsIgnoreCase(difficulty))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> searchByType(String taskType) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getTaskType().equals(taskType))
                .collect(Collectors.toList());
    }
}

