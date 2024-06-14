package de.ait.project_KidVenture.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {



        private Long id;
        private String firstName;
        private String lastName;
        private Integer age;
        private String email;
        private Set<String> roles; // Mainīt uz String vai citu atbilstošu veidu, atkarībā no jūsu Role klases
        private LocalDate registrationDate;
        private boolean isActive;
        private int score;
    }

