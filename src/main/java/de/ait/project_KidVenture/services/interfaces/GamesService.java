package de.ait.project_KidVenture.services.interfaces;

import de.ait.project_KidVenture.entity.Games;

import java.util.List;

public interface GamesService {

    List<Games> getAllGames();

    Games getGameById(Long id);

    Games createGame(Games games);

    Games updateGame(Games games);

    void deleteGameById(Long id);

    List<Games> searchGameByDifficulty(String difficulty);

    List<Games> searchByType(String taskType);


}
