package de.ait.project_KidVenture.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "user ID", example = "1")
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Size(max = 30, message = "Max 30 symbols")
    @Pattern(regexp = "[a-zA-Z]+", message = "First name must contain only letters")
    @Schema(description = "User firstName", example = "James")
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 30, message = "Max 30 symbols")
    @Pattern(regexp = "[a-zA-Z]+", message = "First name must contain only letters")
    @Schema(description = "User firstName", example = "James")
    private String lastName;

    @Column(name = "age", nullable = false)
    @NotNull
    @Min(0)
    @Max(120)
    private Integer age;

    @Column(name = "gender", nullable = false)
    @NotBlank(message = "Required")
    @Schema(description = "Users gender", example = "Male/Female")
    private String gender;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Required")
    @Size(max = 30, message = "Max 30 symbols")
    @Email(message = "Invalid email format!")
    @Schema(description = "User email", example = "may@gmail.com")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Required")
    @Size(min = 60, max = 60)
    @Schema(description = "User password", example = "Password12#")
    private String password;

    @Column(name = "registration_date", nullable = false)
    @NotNull(message = "Registration date must not be null")
    private LocalDateTime registrationDate;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reward> reward = new ArrayList<>();

    @Column(name = "score")
    @NotNull(message = "Score must not be null")
    @Min(value = 0, message = "Score must be at least 0")
    @Max(value = 100, message = "Score must be at most 100")
    private int score;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Можно добавить логику проверки на истекший срок учетной записи
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Можно добавить логику проверки на блокировку учетной записи
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Можно добавить логику проверки на истекшие учетные данные
    }

    @Override
    public boolean isEnabled() {
        return isActive; // Можно добавить дополнительную логику активации учетной записи
    }
}
