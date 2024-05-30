package de.ait.project_KidVenture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "achievement")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "achievementDescription")
    private String achievementDescription;

    @Column(name = "criteria")
    private String criteria;

    @ManyToOne
    @JoinColumn(name = "reward_id")
    private Reward reward;
}
