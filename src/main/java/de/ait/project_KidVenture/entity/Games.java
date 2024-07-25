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

    //TODO category add

    @Column(name = "title")
    private String title;

    @Column(name = "games_description")
    private String gamesDescription;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    @Column(name = "games_type")
    private String gamesType;

    @Column(name = "age_group")
    private String ageGroup;

    @Column(name = "games_content", columnDefinition = "TEXT")
    private String gamesContent;

    @Column(name = "game_category")
    private String gameCategory;

    @Column(name = "correct_answer")
    private String correctAnswer;

    public Games(String ageGroup, String difficultyLevel, String gamesType) {
        this.ageGroup = ageGroup;
        this.difficultyLevel = difficultyLevel;
        this.gamesType = gamesType;
    }
}
