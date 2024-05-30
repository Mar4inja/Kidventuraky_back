package de.ait.project_KidVenture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "difficultyLevel")
    private String difficultyLevel;

    @Column(name = "taskType")
    private String taskType;

    @Column(name = "correctAnswer")
    private String correctAnswer;
}
