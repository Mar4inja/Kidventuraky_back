package de.ait.project_KidVenture.controller;

import de.ait.project_KidVenture.entity.Achievement;
import de.ait.project_KidVenture.services.AchievementService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/achievements")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;

    @Operation(summary = "Find Achievement by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Achievement>> getAchievementById(@PathVariable Long id) {
        return ResponseEntity.ok(achievementService.getAchievementById(id));
    }

    @Operation(summary = "Get all Achievements")
    @GetMapping("/getAll")
    public ResponseEntity<List<Achievement>> getAllAchievements() {
        return ResponseEntity.ok(achievementService.getAllAchievements());
    }

    @Operation(summary = "Find Achievement by Title")
    @GetMapping("/title")
    public ResponseEntity<List<Achievement>> getAchievementByTitle(@RequestParam String title) {
        return ResponseEntity.ok(achievementService.getAchievementsByTitle(title));
    }

    @Operation(summary = "Create new Achievement")
    @PostMapping("/create")
    public ResponseEntity<Achievement> updateAchievement(@RequestBody Achievement achievement) {
        return ResponseEntity.ok(achievementService.createAchievement(achievement));
    }

    @Operation(summary = "Update Achievement")
    @PutMapping("/update/{id}")
    public ResponseEntity<Achievement> updateAchievement(@PathVariable Long id, @RequestBody Achievement achievement) {
        return ResponseEntity.ok(achievementService.updateAchievement(id, achievement));
    }

    @Operation(summary = "Delete Achievement by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAchievement(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
        return ResponseEntity.ok("Deleted Achievement by ID " + id);

    }




}
