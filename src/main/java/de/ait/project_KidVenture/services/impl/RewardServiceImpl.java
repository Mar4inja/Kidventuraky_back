package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Reward;
import de.ait.project_KidVenture.entity.Task;
import de.ait.project_KidVenture.exceptions.EntityAlreadyExistsException;
import de.ait.project_KidVenture.repository.RewardRepository;
import de.ait.project_KidVenture.services.RewardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final RewardRepository rewardRepository;

    @Override
    public Reward createReward(Reward reward) {
        Optional<Reward> existingReward = rewardRepository.findByNameAndRewardDescription(reward.getName(), reward.getRewardDescription());
        if (existingReward.isPresent()) {
            try {
                throw new EntityAlreadyExistsException("Reward with name " + reward.getName() + " and description " + reward.getRewardDescription() + " already exists");
            } catch (EntityAlreadyExistsException e) {
                throw new RuntimeException(e);
            }
        } else {
            return rewardRepository.save(reward);
        }
    }

    @Override
    public Optional<Reward> getRewardById(Long id) {
        return rewardRepository.findById(id);
    }

    @Override
    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    @Override
    public Reward updateReward(Long id, Reward reward) {
        Optional<Reward> optionalReward = rewardRepository.findById(id);
        if (optionalReward.isPresent()) {
            reward.setId(id);
            return rewardRepository.save(reward);
        } else {
            throw new EntityNotFoundException("Reward with id " + id + " not found");
        }
    }

    @Override
    public void deleteReward(Long id) {
        rewardRepository.deleteById(id);
    }

    @Override
    public List<Reward> findRewardsByType(String rewardType) {
        return rewardRepository.findAll().stream()
                .filter(reward -> reward.getRewardType().equals(rewardType))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reward> findRewardsByPointsRange(int minPoints, int maxPoints) {
        return rewardRepository.findAll().stream()
                .filter(reward -> reward.getPoints() >= minPoints && reward.getPoints() <= maxPoints)
                .collect(Collectors.toList());
    }

    @Override
    public Reward claimReward(Long rewardId, Long userId) {
        // Atrodam balvu pēc tās ID
        Optional<Reward> optionalReward = rewardRepository.findById(rewardId);

        // Pārbaudam, vai balva ir atrasta
        if (optionalReward.isPresent()) {
            // Ja balva ir atrasta, iegūstam to no Optional objekta
            Reward reward = optionalReward.get();

            // Izvadam paziņojumu par to, ka balva ir pieprasīta, norādot balvas nosaukumu un lietotāja ID
            System.out.println("Reward claimed: " + reward.getName() + " by user " + userId);
        } else {
            // Ja balva nav atrasta, izraisa izņēmumu un izvada kļūdas paziņojumu
            throw new EntityNotFoundException("Reward not found");
        }
        return null;
    }
}