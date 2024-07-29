package de.ait.project_KidVenture.services.interfaces;

import de.ait.project_KidVenture.entity.Games;

import java.util.List;

public interface GamesService {

    List<Games> getAllGames();

    Games getGameById(Long id);

    Games createGame(Games games);

    Games updateGame(Games games);

    List<Games> showGamesByGameCategoryAndAge(String gameCategory, String ageGroup);
}
