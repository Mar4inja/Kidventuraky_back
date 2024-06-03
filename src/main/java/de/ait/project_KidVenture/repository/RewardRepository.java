package de.ait.project_KidVenture.repository;

import de.ait.project_KidVenture.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RewardRepository extends JpaRepository<Reward, Long> {

    Optional<Reward> findByNameAndRewardDescription(String name, String rewardDescription);
}
