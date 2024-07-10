package de.ait.project_KidVenture.services.interfaces;

import de.ait.project_KidVenture.entity.Games;

import java.util.List;

public interface GamesService {

    List<Games> getAllTasks();

    Games getTaskById(Long id);

    Games createTask(Games games);

    Games updateTask(Games games);

    void deleteTaskById(Long id);

    List<Games> searchTaskByDifficulty(String difficulty);

    List<Games> searchByType(String taskType);


}
