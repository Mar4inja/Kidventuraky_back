package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Achievement;
import de.ait.project_KidVenture.repository.AchievementRepository;
import de.ait.project_KidVenture.services.interfaces.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AchievementServiceImp implements AchievementService {


    private final AchievementRepository achievementRepository;

    @Override
    public Achievement createAchievement(Achievement achievement) {
        achievement.setId(null);

        if (achievement.getTitle() == null || achievement.getTitle().isEmpty()
                || achievement.getAchievementDescription() == null || achievement.getAchievementDescription().isEmpty()
                || achievement.getCriteria() == null || achievement.getCriteria().isEmpty()) {
            throw new IllegalArgumentException("All fields must be filled");
        }
        Achievement existingAchievement = achievementRepository.findByTitle(achievement.getTitle());
        if (existingAchievement != null) {
            throw new IllegalArgumentException("Achievement already exists");
        } else {
            Achievement savedAchievement = achievementRepository.save(achievement);
            return savedAchievement;
        }
    }


    @Override
    public Optional<Achievement> getAchievementById(Long id) {
        return achievementRepository.findAll().stream()
                .filter(achievement -> achievement.getId().equals(id))
                .findFirst();
    }

    @Override
    public Achievement updateAchievement(Long id, Achievement achievement) {
        Optional<Achievement> achievementOptional = achievementRepository.findById(id);

        if (achievementOptional.isPresent()) {
            Achievement existingAchievement = achievementOptional.get();

            if (achievement.getTitle() != null && !achievement.getTitle().isEmpty()) {
                existingAchievement.setTitle(achievement.getTitle());
            }
            if (achievement.getAchievementDescription() != null && !achievement.getAchievementDescription().isEmpty()) {
                existingAchievement.setAchievementDescription(achievement.getAchievementDescription());
            }
            if (achievement.getCriteria() != null && !achievement.getCriteria().isEmpty()) {
                existingAchievement.setCriteria(achievement.getCriteria());
            }
            if (achievement.getReward() != null) { // Ja vajag atjaunināt arī reward
                existingAchievement.setReward(achievement.getReward());
            }

            return achievementRepository.save(existingAchievement);
        } else {
            throw new IllegalArgumentException("Achievement not found");
        }
    }


    @Override
    public void deleteAchievement(Long id) {
        if (achievementRepository.existsById(id)) {
            achievementRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Achievement does not exist");
        }
    }

    @Override
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    @Override
    public List<Achievement> getAchievementsByTitle(String title) {
        return achievementRepository.findAll().stream()
                .filter(achievement -> achievement.getTitle().equals(title))
                .collect(Collectors.toList());
    }


}
