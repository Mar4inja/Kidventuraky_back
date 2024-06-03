package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.Reward;
import de.ait.project_KidVenture.services.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    @PostMapping("/create")
    public ResponseEntity<Reward> createReward(@RequestBody Reward reward) {
        Reward createdReward = rewardService.createReward(reward);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReward);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reward> getRewardById(@PathVariable Long id) {
        Optional<Reward> reward = rewardService.getRewardById(id);
        return reward.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Reward> updateReward(@RequestBody Reward reward, @PathVariable Long id) {
        Reward updatedReward = rewardService.updateReward(id, reward);
        return ResponseEntity.ok(updatedReward);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Reward>> getRewardsByType(@PathVariable String type) {
        List<Reward> rewardsByType = rewardService.findRewardsByType(type);
        return ResponseEntity.ok(rewardsByType);
    }

    @GetMapping("/points/{minPoints}/{maxPoints}")
    public ResponseEntity<List<Reward>> getRewardsByPointRange(@PathVariable int minPoints, @PathVariable int maxPoints) {
        List<Reward> rewardsByPointsRange = rewardService.findRewardsByPointsRange(minPoints, maxPoints);
        return ResponseEntity.ok(rewardsByPointsRange);
    }

    @GetMapping("/claim/{rewardId}/{userId}")
    public ResponseEntity<Reward> claimReward(@PathVariable Long rewardId, @PathVariable Long userId) {
        Reward claimedReward = rewardService.claimReward(rewardId, userId);
        return ResponseEntity.ok(claimedReward);
    }
}
