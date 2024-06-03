package de.ait.project_KidVenture.services.impl;

import de.ait.project_KidVenture.entity.Leaderboard;
import de.ait.project_KidVenture.repository.LeaderboardRepository;
import de.ait.project_KidVenture.services.LeaderboardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;

    // Metode, kas izveido jaunu ierakstu vadības tabulā
    @Override
    public Leaderboard createLeaderboardEntry(Leaderboard leaderboard) {
        return leaderboardRepository.save(leaderboard); // Izveido jaunu ierakstu vadības tabulā, izmantojot repository objektu
    }

    // Metode, kas atgriež ierakstu vadības tabulā pēc norādītā identifikatora
    @Override
    public Optional<Leaderboard> getLeaderboardEntryById(Long id) {
        return leaderboardRepository.findById(id); // Atgriež ierakstu vadības tabulā pēc identifikatora, ja tāds eksistē
    }

    // Metode, kas atjauno esošo ierakstu vadības tabulā
    @Override
    public Leaderboard updateLeaderboardEntry(Long id, Leaderboard leaderboard) {
        Optional<Leaderboard> existingEntry = leaderboardRepository.findById(id); // Meklē esošo ierakstu pēc identifikatora
        if (existingEntry.isPresent()) { // Ja tāds eksistē
            leaderboard.setId(id); // Saglabā esošo ID
            return leaderboardRepository.save(leaderboard); // Atjauno ierakstu vadības tabulā ar jaunajiem datiem
        } else {
            throw new EntityNotFoundException("Leaderboard entry not found"); // Ja ieraksts nav atrasts, izmet izņēmumu
        }
    }

    // Metode, kas dzēš ierakstu vadības tabulā pēc norādītā identifikatora
    @Override
    public void deleteLeaderboardEntry(Long id) {
        if (leaderboardRepository.existsById(id)) { // Ja ieraksts ar norādīto identifikatoru pastāv
            leaderboardRepository.deleteById(id); // Dzēš ierakstu vadības tabulā
        } else {
            throw new EntityNotFoundException("Leaderboard entry not found"); // Ja ieraksts nav atrasts, izmet izņēmumu
        }
    }

    // Metode, kas atgriež lietotāja rangu uz norādītu uzdevumu, ja tāds pastāv
    @Override
    public Optional<Leaderboard> getUserRankForTask(Long userId, Long taskId) {
        return leaderboardRepository.findByUserIdAndTaskId(userId, taskId); // Atgriež ierakstu vadības tabulā, ja tāds pastāv
    }

    @Override
    public List<Leaderboard> getTopUsersForTask(Long taskId, Long userId) {
        Optional<Leaderboard> leaderboard = leaderboardRepository.findByUserIdAndTaskId(userId, taskId);
        if (leaderboard.isPresent()) {
            // Ja lietotājs ir atrasts, varat atgriezt visus rezultātus šajā uzdevumā, ja nepieciešams
            return leaderboardRepository.findTopByTaskIdOrderByRankAsc(taskId, PageRequest.of(0, 10));
        } else {
            // Ja lietotājs nav atrasts, varat atgriezt tukšu sarakstu vai kaut ko citu
            return Collections.emptyList();
        }
    }



    // Metode, kas atjauno lietotāja rangu un rezultātu
    @Override
    public Leaderboard updateUserRankAndScore(Long userId, Long taskId, int rank, int score) {
        return null; // Šī metode vēl nav implementēta
    }

    // Metode, kas atgriež visus ierakstus vadības tabulā par norādīto uzdevumu
    @Override
    public List<Leaderboard> getAllEntriesForTask(Long taskId) {
        List<Leaderboard> leaderboard = leaderboardRepository.findAll(); // Atgriež visus ierakstus vadības tabulā
        return leaderboard; // Atgriež visus ierakstus vadības tabulā
    }
}
