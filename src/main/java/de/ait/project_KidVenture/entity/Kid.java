package de.ait.project_KidVenture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "kid")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Kid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;


    @Column(name = "email")
    private String email;

    @Column(name = "registrationDate")
    private LocalDate registrationDate;
}
