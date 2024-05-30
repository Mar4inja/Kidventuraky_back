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
    @JoinColumn(name = "kid_id", nullable = false)
    private Kid kid;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "completed_tasks", nullable = false)
    private int completedTasks;

    @Column(name = "total_score", nullable = false)
    private int totalScore;

    @Column(name = "progress_percentage", nullable = false)
    private double progressPercentage;

}
