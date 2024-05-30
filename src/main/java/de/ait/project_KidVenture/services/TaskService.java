package de.ait.project_KidVenture.services;

import de.ait.project_KidVenture.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task createTask(Task task);

    Task updateTask(Task task);

    void deleteTask(Long id);

    List<Task> searchTaskByDifficulty(String difficulty);

    List<Task> searchByType(String taskType);


}
