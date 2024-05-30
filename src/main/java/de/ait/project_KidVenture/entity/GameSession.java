package de.ait.project_KidVenture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_session")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kid_id")
    private Kid kid;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @Column(name = "score")
    private double score;
}
