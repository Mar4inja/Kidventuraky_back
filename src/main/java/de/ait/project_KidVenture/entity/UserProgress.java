package de.ait.project_KidVenture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_progress")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "games_id", nullable = false)
    private Games games;

    @Column(name = "completed_games", nullable = false)
    private int completedGames;

    @Column(name = "total_score", nullable = false)
    private int totalScore;

    @Column(name = "progress_percentage", nullable = false)
    private double progressPercentage;

}
