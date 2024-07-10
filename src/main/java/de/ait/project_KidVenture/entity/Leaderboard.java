package de.ait.project_KidVenture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "leaderboard")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Games games;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "rank")
    private int rank;

    @Column(name = "score")
    private int score;

}
