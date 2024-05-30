package de.ait.project_KidVenture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reward")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "reward_description")
    private String rewardDescription;

    @Column(name = "points")
    private int points;

    @Column(name = "reward_type")
    private String rewardType;

}
