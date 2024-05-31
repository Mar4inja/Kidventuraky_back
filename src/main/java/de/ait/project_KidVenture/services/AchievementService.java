package de.ait.project_KidVenture.services;

import de.ait.project_KidVenture.entity.Achievement;

import java.util.List;
import java.util.Optional;

public interface AchievementService {


        Achievement createAchievement(Achievement achievement);

        Optional<Achievement> getAchievementById(Long id);

        Achievement updateAchievement(Long id, Achievement achievement);

        void deleteAchievement(Long id);

        List<Achievement> getAllAchievements();

        List<Achievement> getAchievementsByTitle(String title);

    }


