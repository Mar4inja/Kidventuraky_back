package de.ait.project_KidVenture.services.interfaces;

import de.ait.project_KidVenture.entity.Reward;

import java.util.List;
import java.util.Optional;

public interface RewardService {

    // Create a new reward
    Reward createReward(Reward reward);

    // Get a reward by ID
    Optional<Reward> getRewardById(Long id);

    // Get all rewards
    List<Reward> getAllRewards();

    // Update an existing reward
    Reward updateReward(Long id, Reward reward);

    // Delete a reward by ID
    void deleteReward(Long id);

    // Find rewards by type
    List<Reward> findRewardsByType(String rewardType);

    // Find rewards by points range
    List<Reward> findRewardsByPointsRange(int minPoints, int maxPoints);

    // Claim a reward (this could include logic to associate the reward with a user)
    Reward claimReward(Long rewardId, Long userId);
}

