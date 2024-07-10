package de.ait.project_KidVenture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "games")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "games_description")
    private String gamesDescription;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @Column(name = "games_type")
    private String gamesType;

    @Column(name = "games_content", columnDefinition = "TEXT")
    private String gamesContent;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
